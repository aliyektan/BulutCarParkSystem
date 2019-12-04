package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.ParkingEventDTO;
import com.aliyektan.bulut.entity.ParkingEvent;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.mapper.ParkingEventMapper;
import com.aliyektan.bulut.repository.ParkingEventRepository;
import com.aliyektan.bulut.service.base.ParkingEventService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ParkingEventServiceImpl implements ParkingEventService<ParkingEventDTO, Integer> {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    @Autowired
    private ParkingEventMapper parkingEventMapper;

    @Autowired
    private UserUtil userUtil;

    @Override
    public ParkingEventDTO save(ParkingEventDTO dto) {
        ParkingEvent parkingEvent = parkingEventMapper.toEntity(dto);
        parkingEvent = parkingEventRepository.save(parkingEvent);
        return parkingEventMapper.toDTO(parkingEvent);
    }

    @Override
    public ParkingEventDTO findById(Integer id) {
        return parkingEventMapper.toDTO(parkingEventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı park bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        parkingEventRepository.deleteById(id);
    }

    @Override
    public List<ParkingEventDTO> findAll() {
        return parkingEventMapper.toDTOList(parkingEventRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

    // TODO: 4.12.2019 implement the available park point count calculator method
    @Override
    public boolean startParking(LicenseNumberDTO licenseNumberDTO) {

        try {
            User creator = userUtil.getAuthenticatedUser();

            if (creator.getRelatedBranch().getPricingList() == null ||
                    creator.getRelatedBranch().getParkPointCount() == null ||
                    creator.getRelatedBranch().getParkPointCount() == 0)
                return false;

            ParkingEvent parkingEvent = new ParkingEvent();
            parkingEvent.setLicenseNumber(licenseNumberDTO.getLicenseNumber());
            parkingEvent.setCurrentBranch(creator.getRelatedBranch());
            parkingEvent.setCreator(creator);
            parkingEvent.setStartDate(new Timestamp(System.currentTimeMillis()));

            parkingEventRepository.save(parkingEvent);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ParkingEvent stopParking(LicenseNumberDTO licenseNumberDTO) {

        ParkingEvent parkingEvent = parkingEventRepository
                .findByLicenseNumberAndEndDateIsNull(licenseNumberDTO.getLicenseNumber())
                .orElseThrow(() -> new EntityNotFoundException("Aktif park islemi bulunamadi"));
        parkingEvent.setEndDate(new Timestamp(System.currentTimeMillis()));

        return parkingEventRepository.save(parkingEvent);

    }

    @Override
    public ParkingEventDTO getActiveParkingEvent(LicenseNumberDTO licenseNumberDTO) {

        ParkingEvent parkingEvent = parkingEventRepository
                .findByLicenseNumberAndEndDateIsNull(licenseNumberDTO.getLicenseNumber())
                .orElseThrow(() -> new EntityNotFoundException("Aktif park islemi bulunamadi"));


        return parkingEventMapper.toDTO(parkingEvent);
    }

}

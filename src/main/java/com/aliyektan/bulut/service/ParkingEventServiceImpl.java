package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.ParkingEventDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.entity.ParkingEvent;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.mapper.ParkingEventMapper;
import com.aliyektan.bulut.repository.ParkingEventRepository;
import com.aliyektan.bulut.service.base.BranchService;
import com.aliyektan.bulut.service.base.ParkingEventService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingEventServiceImpl implements ParkingEventService<ParkingEventDTO, Integer> {

    @Autowired
    private BranchService branchService;

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

        ParkingEvent parkingEvent = parkingEventRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " numaralı park bulunamadı !")
                );

        Branch currentBranch = userUtil.getAuthenticatedUser().getRelatedBranch();

        if (currentBranch != null) {
            if (currentBranch.getId().equals(parkingEvent.getCurrentBranch().getId())) {
                return parkingEventMapper.toDTO(parkingEvent);
            } else {
                throw new EntityNotFoundException(id + " numaralı park bulunamadı !");
            }
        }

        return parkingEventMapper.toDTO(parkingEvent);
    }

    @Override
    public void deleteById(Integer id) {
        parkingEventRepository.deleteById(id);
    }

    @Override
    public List<ParkingEventDTO> findAll() {
        Branch currentBranch = userUtil.getAuthenticatedUser().getRelatedBranch();

        if (currentBranch != null)
            return parkingEventMapper
                    .toDTOList(
                            parkingEventRepository
                                    .findByCurrentBranch_Id(
                                            currentBranch.getId(),
                                            Sort.by(Sort.Direction.DESC, "updatedAt")
                                    )
                    );

        return parkingEventMapper
                .toDTOList(
                        parkingEventRepository.findAll(
                                Sort.by(
                                        Sort.Direction.DESC, "updatedAt")
                        )
                );
    }

    @Override
    public boolean startParking(LicenseNumberDTO licenseNumberDTO) throws Exception {


        User creator = userUtil.getAuthenticatedUser();

        Integer availableParkPointCount = branchService
                .getAvailableParkPointCount();

        Optional<ParkingEvent> active = parkingEventRepository
                .findByLicenseNumberAndEndDateIsNull(licenseNumberDTO.getLicenseNumber());

        if (active.isPresent())
            throw new Exception(licenseNumberDTO.getLicenseNumber() + " plakalı araç şuan zaten park halinde.");

        if (availableParkPointCount <= 0)
            throw new Exception("Müsait park alanı bulunamadı.");

        if (creator.getRelatedBranch().getPricingList() == null ||
                creator.getRelatedBranch().getParkPointCount() == null ||
                creator.getRelatedBranch().getParkPointCount() == 0)
            throw new Exception("Ücretlendirme veya Park Alanları ile ilgili bir hata oluştu. Lütfen sistem yöneticiniz ile iletişime geçiniz.");

        ParkingEvent parkingEvent = new ParkingEvent();
        parkingEvent.setLicenseNumber(licenseNumberDTO.getLicenseNumber());
        parkingEvent.setCurrentBranch(creator.getRelatedBranch());
        parkingEvent.setCreator(creator);
        parkingEvent.setStartDate(new Timestamp(System.currentTimeMillis()));

        parkingEventRepository.save(parkingEvent);

        return true;


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

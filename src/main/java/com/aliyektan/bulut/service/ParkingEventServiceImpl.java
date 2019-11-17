package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.ParkingEventDTO;
import com.aliyektan.bulut.entity.ParkingEvent;
import com.aliyektan.bulut.mapper.ParkingEventMapper;
import com.aliyektan.bulut.repository.ParkingEventRepository;
import com.aliyektan.bulut.service.base.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ParkingEventServiceImpl implements ParkingEventService<ParkingEventDTO, Integer> {

    @Autowired
    private ParkingEventRepository parkingEventRepository;

    @Autowired
    private ParkingEventMapper parkingEventMapper;

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

}

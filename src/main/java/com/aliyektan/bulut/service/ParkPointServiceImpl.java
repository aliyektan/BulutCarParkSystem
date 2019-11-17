package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.ParkPointDTO;
import com.aliyektan.bulut.entity.ParkPoint;
import com.aliyektan.bulut.mapper.ParkPointMapper;
import com.aliyektan.bulut.repository.ParkPointRepository;
import com.aliyektan.bulut.service.base.ParkPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ParkPointServiceImpl implements ParkPointService<ParkPointDTO, Integer> {

    @Autowired
    private ParkPointRepository parkPointRepository;

    @Autowired
    private ParkPointMapper parkPointMapper;

    @Override
    public ParkPointDTO save(ParkPointDTO dto) {
        ParkPoint parkPoint = parkPointMapper.toEntity(dto);
        parkPoint = parkPointRepository.save(parkPoint);
        return parkPointMapper.toDTO(parkPoint);
    }

    @Override
    public ParkPointDTO findById(Integer id) {
        return parkPointMapper.toDTO(parkPointRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı park alanı bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        parkPointRepository.deleteById(id);
    }

    @Override
    public List<ParkPointDTO> findAll() {
        return parkPointMapper.toDTOList(parkPointRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

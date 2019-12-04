package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.PricingPeriodDTO;
import com.aliyektan.bulut.entity.PricingPeriod;
import com.aliyektan.bulut.mapper.PricingPeriodMapper;
import com.aliyektan.bulut.repository.PricingPeriodRepository;
import com.aliyektan.bulut.service.base.PricingPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PricingPeriodServiceImpl implements PricingPeriodService<PricingPeriodDTO, Integer> {

    @Autowired
    private PricingPeriodRepository pricingPeriodRepository;

    @Autowired
    private PricingPeriodMapper pricingPeriodMapper;

    @Override
    public PricingPeriodDTO save(PricingPeriodDTO dto) {
        PricingPeriod pricingPeriod = pricingPeriodMapper.toEntity(dto);
        pricingPeriod = pricingPeriodRepository.save(pricingPeriod);
        return pricingPeriodMapper.toDTO(pricingPeriod);
    }

    @Override
    public PricingPeriodDTO findById(Integer id) {
        return pricingPeriodMapper.toDTO(pricingPeriodRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı ücretlendirme tipi bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        pricingPeriodRepository.deleteById(id);
    }

    @Override
    public List<PricingPeriodDTO> findAll() {
        return pricingPeriodMapper.toDTOList(pricingPeriodRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

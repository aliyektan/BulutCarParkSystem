package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.PricingDTO;
import com.aliyektan.bulut.entity.Pricing;
import com.aliyektan.bulut.mapper.PricingMapper;
import com.aliyektan.bulut.repository.PricingRepository;
import com.aliyektan.bulut.service.base.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PricingServiceImpl implements PricingService<PricingDTO, Integer> {

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private PricingMapper pricingMapper;

    @Override
    public PricingDTO save(PricingDTO dto) {
        Pricing pricing = pricingMapper.toEntity(dto);
        pricing = pricingRepository.save(pricing);
        return pricingMapper.toDTO(pricing);
    }

    @Override
    public PricingDTO findById(Integer id) {
        return pricingMapper.toDTO(pricingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı ücretlendirme bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        pricingRepository.deleteById(id);
    }

    @Override
    public List<PricingDTO> findAll() {
        return pricingMapper.toDTOList(pricingRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

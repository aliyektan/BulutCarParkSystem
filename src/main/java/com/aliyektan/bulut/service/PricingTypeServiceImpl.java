package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.PricingTypeDTO;
import com.aliyektan.bulut.entity.PricingType;
import com.aliyektan.bulut.mapper.PricingTypeMapper;
import com.aliyektan.bulut.repository.PricingTypeRepository;
import com.aliyektan.bulut.service.base.PricingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PricingTypeServiceImpl implements PricingTypeService<PricingTypeDTO, Integer> {

    @Autowired
    private PricingTypeRepository pricingTypeRepository;

    @Autowired
    private PricingTypeMapper pricingTypeMapper;

    @Override
    public PricingTypeDTO save(PricingTypeDTO dto) {
        PricingType pricingType = pricingTypeMapper.toEntity(dto);
        pricingType = pricingTypeRepository.save(pricingType);
        return pricingTypeMapper.toDTO(pricingType);
    }

    @Override
    public PricingTypeDTO findById(Integer id) {
        return pricingTypeMapper.toDTO(pricingTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı ücretlendirme tipi bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        pricingTypeRepository.deleteById(id);
    }

    @Override
    public List<PricingTypeDTO> findAll() {
        return pricingTypeMapper.toDTOList(pricingTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

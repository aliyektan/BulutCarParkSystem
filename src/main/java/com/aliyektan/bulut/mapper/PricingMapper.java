package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.PricingDTO;
import com.aliyektan.bulut.entity.Pricing;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PricingTypeMapper.class})
public interface PricingMapper {

    @Named("toEntity")
    Pricing toEntity(PricingDTO dto);

    @Named("toDTO")
    PricingDTO toDTO(Pricing entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Pricing> toEntityList(List<PricingDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<PricingDTO> toDTOList(List<Pricing> entityList);

}

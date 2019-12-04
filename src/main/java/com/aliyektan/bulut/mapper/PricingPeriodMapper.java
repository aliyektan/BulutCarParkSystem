package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.PricingPeriodDTO;
import com.aliyektan.bulut.entity.PricingPeriod;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricingPeriodMapper {

    @Named("toEntity")
    PricingPeriod toEntity(PricingPeriodDTO dto);

    @Named("toDTO")
    PricingPeriodDTO toDTO(PricingPeriod entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<PricingPeriod> toEntityList(List<PricingPeriodDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<PricingPeriodDTO> toDTOList(List<PricingPeriod> entityList);

}

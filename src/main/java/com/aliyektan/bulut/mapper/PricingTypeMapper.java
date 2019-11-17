package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.PricingTypeDTO;
import com.aliyektan.bulut.entity.PricingType;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricingTypeMapper {

    @Named("toEntity")
    PricingType toEntity(PricingTypeDTO dto);

    @Named("toDTO")
    PricingTypeDTO toDTO(PricingType entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<PricingType> toEntityList(List<PricingTypeDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<PricingTypeDTO> toDTOList(List<PricingType> entityList);

}

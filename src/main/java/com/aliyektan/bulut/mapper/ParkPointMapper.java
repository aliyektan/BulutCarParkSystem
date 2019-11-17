package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.ParkPointDTO;
import com.aliyektan.bulut.entity.ParkPoint;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface ParkPointMapper {

    @Named("toEntity")
    ParkPoint toEntity(ParkPointDTO dto);

    @Named("toDTO")
    ParkPointDTO toDTO(ParkPoint entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<ParkPoint> toEntityList(List<ParkPointDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<ParkPointDTO> toDTOList(List<ParkPoint> entityList);

}

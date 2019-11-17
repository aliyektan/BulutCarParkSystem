package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.ParkingEventDTO;
import com.aliyektan.bulut.entity.ParkingEvent;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParkPointMapper.class})
public interface ParkingEventMapper {

    @Named("toEntity")
    ParkingEvent toEntity(ParkingEventDTO dto);

    @Named("toDTO")
    ParkingEventDTO toDTO(ParkingEvent entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<ParkingEvent> toEntityList(List<ParkingEventDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<ParkingEventDTO> toDTOList(List<ParkingEvent> entityList);

}

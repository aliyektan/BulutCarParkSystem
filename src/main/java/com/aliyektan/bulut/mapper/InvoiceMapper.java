package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.InvoiceDTO;
import com.aliyektan.bulut.entity.Invoice;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ParkingEventMapper.class})
public interface InvoiceMapper {

    @Named("toEntity")
    Invoice toEntity(InvoiceDTO dto);

    @Named("toDTO")
    InvoiceDTO toDTO(Invoice entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Invoice> toEntityList(List<InvoiceDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<InvoiceDTO> toDTOList(List<Invoice> entityList);

}

package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.entity.Branch;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PricingMapper.class})
public interface BranchMapper {

    @Named("toEntity")
    Branch toEntity(BranchDTO dto);

    @Named("toDTO")
    BranchDTO toDTO(Branch entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Branch> toEntityList(List<BranchDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<BranchDTO> toDTOList(List<Branch> entityList);

}

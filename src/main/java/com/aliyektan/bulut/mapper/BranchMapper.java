package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.repository.BranchRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PricingMapper.class})
public abstract class BranchMapper {

    @Autowired
    private BranchRepository branchRepository;

    // AVAILABLE PARKPOINTS WILL RETRIEVE AND PUT INTO DTO
    @Named("toEntity")
    public abstract Branch toEntity(BranchDTO dto);

    @Named("toDTO")
    public abstract BranchDTO toDTO(Branch entity);

    @IterableMapping(qualifiedByName = "toEntity")
    public abstract List<Branch> toEntityList(List<BranchDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    public abstract List<BranchDTO> toDTOList(List<Branch> entityList);

    @AfterMapping
    void afterToDTO(@MappingTarget BranchDTO target, Branch branch) {
        Integer availableCount = branchRepository.getAvailableParkPointCount(branch.getId());
        target.setAvailableParkPointCount(availableCount);
    }

}

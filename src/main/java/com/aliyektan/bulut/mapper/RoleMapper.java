package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.RoleDTO;
import com.aliyektan.bulut.entity.Role;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    
    @Named("toEntity")
    Role toEntity(RoleDTO dto);
    
    @Named("toDTO")
    RoleDTO toDTO(Role entity);
    
    @IterableMapping(qualifiedByName = "toEntity")
    List<Role> toEntityList(List<RoleDTO> dtoList);
    
    @IterableMapping(qualifiedByName = "toDTO")
    List<RoleDTO> toDTOList(List<Role> entityList);
    
}

package com.aliyektan.bulut.mapper;

import com.aliyektan.bulut.dto.UserDTO;
import com.aliyektan.bulut.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BranchMapper.class, RoleMapper.class})
public interface UserMapper {

    @Named("toEntity")
    User toEntity(UserDTO dto);

    @Named("toDTO")
    UserDTO toDTO(User entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<User> toEntityList(List<UserDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<UserDTO> toDTOList(List<User> entityList);

}

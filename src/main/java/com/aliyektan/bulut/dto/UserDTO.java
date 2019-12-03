package com.aliyektan.bulut.dto;


import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDTO extends BaseDTO {

    private String email;

    private String phoneNumber;

    private String identityNumber;

    private String name;

    private String surname;

    private RoleDTO role;

    private BranchDTO relatedBranch;

}

package com.aliyektan.bulut.entity;


import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Column(length = 10, unique = true)
    private String phoneNumber;

    @NotBlank
    @NotNull
    @Column(length = 11, unique = true)
    private String identityNumber;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch relatedBranch;

}

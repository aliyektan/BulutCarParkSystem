package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "park_points")
public class ParkPoint extends BaseEntity {

    @NotNull
    @NotBlank
    private String serial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Branch branch;

}

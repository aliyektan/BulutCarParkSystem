package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pricing_types", uniqueConstraints = {
        @UniqueConstraint(name = "uq_pricing_type",columnNames = {"start","end"})
})
public class PricingType extends BaseEntity {

    @NotNull
    @NotBlank
    private Double start;

    @NotNull
    @NotBlank
    private Double end;

}

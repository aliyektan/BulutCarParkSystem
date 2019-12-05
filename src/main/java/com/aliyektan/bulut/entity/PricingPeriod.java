package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pricing_periods", uniqueConstraints = {
        @UniqueConstraint(name = "uq_pricing_period_start_end", columnNames = {"start", "end"})
})
public class PricingPeriod extends BaseEntity {

    @NotNull
    @NotBlank
    private Double start;

    @NotNull
    @NotBlank
    private Double end;

}

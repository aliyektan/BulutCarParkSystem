package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pricing", uniqueConstraints = {
        @UniqueConstraint(name = "uq_pricing_period", columnNames = {"price", "pricing_period_id"})
})
public class Pricing extends BaseEntity {


    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private PricingPeriod pricingPeriod;

}

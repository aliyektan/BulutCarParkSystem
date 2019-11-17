package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "branches")
public class Branch extends BaseEntity {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String address;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.REMOVE)
    private List<ParkPoint> parkPoints;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pricing> pricingList;

}

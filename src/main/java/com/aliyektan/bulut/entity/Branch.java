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

    private Integer parkPointCount;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pricing> pricingList;

    @OneToMany(mappedBy = "relatedBranch", cascade = {CascadeType.REMOVE})
    private List<User> relatedStaff;

    @OneToMany(mappedBy = "currentBranch", cascade = {CascadeType.REMOVE})
    private List<ParkingEvent> relatedParkingEvents;


}

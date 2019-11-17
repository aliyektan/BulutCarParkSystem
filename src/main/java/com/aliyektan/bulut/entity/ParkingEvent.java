package com.aliyektan.bulut.entity;

import com.aliyektan.bulut.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "parking_events")
public class ParkingEvent extends BaseEntity {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0[1-9]|[1-7][0-9]|8[01])(([A-Z])(\\d{1,4})|([A-Z]{2})(\\d{2,4})|([A-Z]{3})(\\d{2,4}))$")
    @Column(length = 9)
    private String licenseNumber;

    @NotNull
    @NotBlank
    private Timestamp startDate;

    private Timestamp endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ParkPoint parkPoint;

}

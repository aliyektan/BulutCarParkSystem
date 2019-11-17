package com.aliyektan.bulut.dto;

import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ParkingEventDTO extends BaseDTO {

    private Timestamp startDate;

    private Timestamp endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private ParkPointDTO parkPoint;

}

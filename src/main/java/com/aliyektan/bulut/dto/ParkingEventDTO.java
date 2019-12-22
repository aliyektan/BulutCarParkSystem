package com.aliyektan.bulut.dto;

import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ParkingEventDTO extends BaseDTO {

    private String licenseNumber;

    private Timestamp startDate;

    private Timestamp endDate;

    private BranchDTO currentBranch;

    private UserDTO creator;

}

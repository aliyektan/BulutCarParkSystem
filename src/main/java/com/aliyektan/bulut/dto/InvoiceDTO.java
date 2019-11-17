package com.aliyektan.bulut.dto;

import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvoiceDTO extends BaseDTO {

    private boolean paid;

    private ParkingEventDTO parkingEvent;

}

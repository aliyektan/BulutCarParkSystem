package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.ParkingEventDTO;
import com.aliyektan.bulut.dto.base.BaseDTO;
import com.aliyektan.bulut.entity.ParkingEvent;

public interface ParkingEventService<T extends BaseDTO, ID> extends BaseService<T,ID> {

    boolean startParking(LicenseNumberDTO licenseNumberDTO);

    ParkingEvent stopParking(LicenseNumberDTO licenseNumberDTO);

    ParkingEventDTO getActiveParkingEvent(LicenseNumberDTO licenseNumberDTO);

}

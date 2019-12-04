package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.LicenseNumberDTO;
import com.aliyektan.bulut.dto.base.BaseDTO;

public interface InvoiceService<T extends BaseDTO, ID> extends BaseService<T,ID> {

    T bill(LicenseNumberDTO licenseNumberDTO);

}

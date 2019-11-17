package com.aliyektan.bulut.dto;

import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PricingDTO extends BaseDTO {

    private Double price;

    private PricingTypeDTO pricingType;

}

package com.aliyektan.bulut.dto;

import com.aliyektan.bulut.dto.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BranchDTO extends BaseDTO {

    private String name;

    private String address;

    private List<PricingDTO> pricingList;

}

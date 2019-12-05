package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.dto.base.BaseDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.entity.PricingPeriod;

import java.util.List;

public interface BranchService<T extends BaseDTO, ID> extends BaseService<T,ID> {

    BranchDTO create(BranchDTO branchDTO) throws Exception;

}

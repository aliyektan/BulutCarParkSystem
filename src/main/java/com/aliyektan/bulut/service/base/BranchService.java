package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.dto.base.BaseDTO;

public interface BranchService<T extends BaseDTO, ID> extends BaseService<T, ID> {

    BranchDTO create(BranchDTO branchDTO) throws Exception;

    Integer getAvailableParkPointCount();

    Integer getAvailableParkPointCountByBranchId(Integer id);

}

package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.base.BaseDTO;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseDTO, ID> {

    T save(T dto);

    T findById(ID id);

    void deleteById(ID id);

    List<T> findAll();

}

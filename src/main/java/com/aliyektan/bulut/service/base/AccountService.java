package com.aliyektan.bulut.service.base;

import com.aliyektan.bulut.dto.NewPasswordDTO;

public interface AccountService {

    boolean changePassword(NewPasswordDTO passwordDTO);

}

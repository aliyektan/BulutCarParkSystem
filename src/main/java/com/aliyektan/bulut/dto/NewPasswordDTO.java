package com.aliyektan.bulut.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {

    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;

}

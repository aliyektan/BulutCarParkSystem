package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.NewPasswordDTO;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.repository.UserRepository;
import com.aliyektan.bulut.service.base.AccountService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserUtil userUtil;

    @Override
    public boolean changePassword(NewPasswordDTO passwordDTO) {
        User user = userUtil.getAuthenticatedUser();

        if (user != null && validatePassword(passwordDTO, user)) {

            user.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));

            userRepository.save(user);

            return true;
        } else {
            return false;
        }

    }

    private boolean validatePassword(NewPasswordDTO passwordDTO, User user) {
        if (!bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword()))
            return false;
        else return passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordConfirm());
    }
}

package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.UserDTO;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.mapper.UserMapper;
import com.aliyektan.bulut.repository.UserRepository;
import com.aliyektan.bulut.service.base.UserService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService<UserDTO, Integer> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserUtil userUtil;

    @Override
    public UserDTO save(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        User finalUser = user;
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(finalUser.getId()+ " numaralı kullanıcı bulunamadı !"));
        user.setPassword(userFromDb.getPassword());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getIdentityNumber()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getAuthenticatedUserInfo() {
        return userMapper.toDTO(userUtil.getAuthenticatedUser());
    }

    @Override
    public UserDTO findById(Integer id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı kullanıcı bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDTOList(userRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

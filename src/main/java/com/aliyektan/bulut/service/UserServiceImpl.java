package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.UserDTO;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.mapper.UserMapper;
import com.aliyektan.bulut.repository.UserRepository;
import com.aliyektan.bulut.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService<UserDTO, Integer> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO save(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
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

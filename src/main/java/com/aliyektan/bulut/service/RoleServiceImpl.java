package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.RoleDTO;
import com.aliyektan.bulut.entity.Role;
import com.aliyektan.bulut.mapper.RoleMapper;
import com.aliyektan.bulut.repository.RoleRepository;
import com.aliyektan.bulut.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService<RoleDTO, Integer> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO save(RoleDTO dto) {
        Role role = roleMapper.toEntity(dto);
        role = roleRepository.save(role);
        return roleMapper.toDTO(role);
    }

    @Override
    public RoleDTO findById(Integer id) {
        return roleMapper.toDTO(roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı rol bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleMapper.toDTOList(roleRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

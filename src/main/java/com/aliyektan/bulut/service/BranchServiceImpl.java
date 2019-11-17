package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.mapper.BranchMapper;
import com.aliyektan.bulut.repository.BranchRepository;
import com.aliyektan.bulut.service.base.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService<BranchDTO, Integer> {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    @Override
    public BranchDTO save(BranchDTO dto) {
        Branch branch = branchMapper.toEntity(dto);
        branch = branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    @Override
    public BranchDTO findById(Integer id) {
        return branchMapper.toDTO(branchRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " numaralı şube bulunamadı !")));
    }

    @Override
    public void deleteById(Integer id) {
        branchRepository.deleteById(id);
    }

    @Override
    public List<BranchDTO> findAll() {
        return branchMapper.toDTOList(branchRepository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt")));
    }

}

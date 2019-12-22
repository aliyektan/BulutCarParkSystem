package com.aliyektan.bulut.service;

import com.aliyektan.bulut.dto.BranchDTO;
import com.aliyektan.bulut.entity.Branch;
import com.aliyektan.bulut.entity.Pricing;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.mapper.BranchMapper;
import com.aliyektan.bulut.repository.BranchRepository;
import com.aliyektan.bulut.service.base.BranchService;
import com.aliyektan.bulut.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService<BranchDTO, Integer> {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    @Autowired
    private UserUtil userUtil;

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

    // Example Request
//    {
//        "name": "1234",
//            "address": "dsdsd",
//            "pricingList": [
//        {
//            "price": 5.0,
//                "pricingPeriod": {
//            "start": 0.0,
//                    "end": 3.0
//        }
//        },
//        {
//            "price": 10.0,
//                "pricingPeriod": {
//            "start": 3.0,
//                    "end": 8.0
//        }
//        },
//        {
//            "price": 15.0,
//                "pricingPeriod": {
//            "start": 8.0,
//                    "end": 24.0
//        }
//        }
//    ]
//    }

    // Added create because throw Exception
    @Override
    public BranchDTO create(BranchDTO dto) throws Exception {
        Branch branch = branchMapper.toEntity(dto);
        pricingPeriodValidation(branch.getPricingList());
        branch = branchRepository.save(branch);
        return branchMapper.toDTO(branch);
    }

    @Override
    public Integer getAvailableParkPointCount() {
        User user = userUtil.getAuthenticatedUser();
        return branchRepository.getAvailableParkPointCount(user.getRelatedBranch().getId());
    }

    @Override
    public Integer getAvailableParkPointCountByBranchId(Integer id) {
        return branchRepository.getAvailableParkPointCount(id);
    }

    private void pricingPeriodValidation(List<Pricing> pricing) throws Exception {

        boolean isValid = true;
        double totalHour = 0.0;

        // Rule 1
        isValid = pricing.stream().anyMatch(pricing1 -> pricing1.getPricingPeriod().getStart() == 0);

        // Sorting
        Comparator<Pricing> compareById = Comparator.comparing((Pricing o) -> o.getPricingPeriod().getStart());
        pricing.sort(compareById);

        for (int i = 0; i < pricing.size(); i++) {
            // Rule 2
            if (pricing.get(i).getPricingPeriod().getStart() > pricing.get(i).getPricingPeriod().getEnd()
                    || pricing.get(i).getPricingPeriod().getStart().equals(pricing.get(i).getPricingPeriod().getEnd())) {
                isValid = false;
            } else if ((i + 1) < pricing.size()) {
                // Rule 3
                if (pricing.get(i).getPricingPeriod().getEnd() > pricing.get(i + 1).getPricingPeriod().getStart()) {
                    isValid = false;
                }
            }
            // Rule 4
            totalHour += pricing.get(i).getPricingPeriod().getEnd() - pricing.get(i).getPricingPeriod().getStart();
        }
        if (totalHour != 24 || !isValid) {
            throw new Exception("Fiyat listesi uygun formatta değil. Lütfen kontrol edip tekrar deneyiniz.");
        }
    }


}

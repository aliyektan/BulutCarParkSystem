package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.PricingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingTypeRepository extends JpaRepository<PricingType, Integer> {
}

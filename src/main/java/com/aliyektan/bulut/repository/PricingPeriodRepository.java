package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.PricingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingPeriodRepository extends JpaRepository<PricingPeriod, Integer> {
}

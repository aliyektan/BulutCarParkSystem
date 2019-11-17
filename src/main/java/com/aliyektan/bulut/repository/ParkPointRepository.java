package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.ParkPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkPointRepository extends JpaRepository<ParkPoint, Integer> {
}

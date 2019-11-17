package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.ParkingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Integer> {
}

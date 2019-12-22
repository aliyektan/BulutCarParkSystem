package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.ParkingEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Integer> {

    Optional<ParkingEvent> findByLicenseNumberAndEndDateIsNull(String licenseNumber);

    List<ParkingEvent> findByCurrentBranch_Id(Integer currentBranchId, Sort sort);

}

package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {


    @Query(nativeQuery = true, value = "select b.park_point_count - ape.count " +
            "from branches b, " +
            "     (select count(*) as count from parking_events pe where pe.end_date is null and pe.current_branch_id = :id) ape " +
            "where b.id = :id")
    Integer getAvailableParkPointCount(@Param("id") Integer id);

}

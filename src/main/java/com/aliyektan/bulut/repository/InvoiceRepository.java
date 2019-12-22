package com.aliyektan.bulut.repository;

import com.aliyektan.bulut.entity.Invoice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByParkingEvent_CurrentBranch_Id(Integer branchId, Sort sort);

}

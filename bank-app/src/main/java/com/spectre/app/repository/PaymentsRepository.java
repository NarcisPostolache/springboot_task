package com.spectre.app.repository;

import com.spectre.app.entity.BankOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentsRepository extends JpaRepository<BankOrder, Long> {
    @Query("select order from BankOrder order where order.fromBankAccount.id = ?1 and order.lastModifiedDate between ?2 and ?3")
//    @Query("select order from BankOrder order where order.fromAccount like ?1 and order.lastModifiedDate between ?2 and ?3")
    List<BankOrder> getOrderByIdAndDate(Long id, LocalDate dateFrom, LocalDate dateTo);
}

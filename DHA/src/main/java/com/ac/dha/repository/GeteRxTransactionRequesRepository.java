package com.ac.dha.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entties.GeteRxTransactionReques;

@Repository
public interface GeteRxTransactionRequesRepository extends JpaRepository<GeteRxTransactionReques, Long> {
}
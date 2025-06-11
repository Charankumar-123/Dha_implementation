package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entties.GetNewTransactionsRequest;
import com.ac.dha.entties.GeteRxTransactionReques;

@Repository
public interface GetNewTransactionRepository extends JpaRepository<GetNewTransactionsRequest, Long> {

}

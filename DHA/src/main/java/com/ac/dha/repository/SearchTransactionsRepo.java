package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entities.SearchTransactionsRequest;

@Repository
public interface SearchTransactionsRepo extends JpaRepository<SearchTransactionsRequest, Long> {

}

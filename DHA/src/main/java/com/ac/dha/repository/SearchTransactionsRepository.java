package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entities.SearchTransactionsRequest;

public interface SearchTransactionsRepository extends JpaRepository<SearchTransactionsRequest, Long> {

}

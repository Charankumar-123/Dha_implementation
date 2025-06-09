package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entties.SetTransactionDownloadedRequest;

@Repository
public interface SetTransactionDownRepo extends JpaRepository<SetTransactionDownloadedRequest, Long> {

}

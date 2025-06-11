package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entities.SetTransactionDownloadedRequest;

public interface SetTransactionDownloadedRepository extends JpaRepository<SetTransactionDownloadedRequest, Long> {

}

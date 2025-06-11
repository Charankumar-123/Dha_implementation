package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entities.DownloadTransactionFileRequest;

@Repository
public interface DownloadTransactionFileRepo extends JpaRepository<DownloadTransactionFileRequest, Long> {

}

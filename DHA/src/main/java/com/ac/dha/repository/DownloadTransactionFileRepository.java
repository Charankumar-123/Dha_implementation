package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entities.DownloadTransactionFileRecord;

public interface DownloadTransactionFileRepository extends JpaRepository<DownloadTransactionFileRecord , Long> {

}

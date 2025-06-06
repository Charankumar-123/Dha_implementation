package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entties.UploadERxRequest;

@Repository
public interface UploadERxRequestRepository extends JpaRepository<UploadERxRequest, Long> {
}
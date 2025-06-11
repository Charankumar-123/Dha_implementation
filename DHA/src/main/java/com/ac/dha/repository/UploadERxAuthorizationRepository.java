package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entities.UploadERxAuthorization;

@Repository
public interface UploadERxAuthorizationRepository extends JpaRepository<UploadERxAuthorization, Long> {

}

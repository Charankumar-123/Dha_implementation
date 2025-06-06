package com.ac.dha.repository;


import com.ac.dha.entities.UploadErxRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadErxRequestRepository extends JpaRepository<UploadErxRequest, Long> {
}
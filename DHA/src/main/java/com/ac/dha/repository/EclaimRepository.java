package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entities.PriorRequest;

@Repository
public interface EclaimRepository extends JpaRepository<PriorRequest, Long> {
}

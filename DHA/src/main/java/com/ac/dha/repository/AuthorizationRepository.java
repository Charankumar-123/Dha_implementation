package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entities.Authorization;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
}


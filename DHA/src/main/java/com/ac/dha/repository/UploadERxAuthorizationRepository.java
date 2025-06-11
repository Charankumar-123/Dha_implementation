package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ac.dha.entties.UploadERxAuthorization;
@Repository
public interface UploadERxAuthorizationRepository extends JpaRepository<UploadERxAuthorization, Integer>{

}

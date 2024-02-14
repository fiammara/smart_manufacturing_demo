package com.manufacture.expertservice.repository;

import com.manufacture.expertservice.model.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {

}

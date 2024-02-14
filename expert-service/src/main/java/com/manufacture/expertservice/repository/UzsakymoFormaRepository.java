package com.manufacture.expertservice.repository;

import com.manufacture.expertservice.model.UzsakymoForma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UzsakymoFormaRepository extends JpaRepository<UzsakymoForma, Long> {

    UzsakymoForma findTop1ByCompanyidOrderByIdDesc(String companyId);

}

package com.example.demoulid;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EcritureJGRepository extends CrudRepository<EcritureJournalGeneral, Long> {


    List<EcritureJournalGeneral> findAllByRegieAndAnneeOrderByUlidAsc(String Regie, int annee);
}

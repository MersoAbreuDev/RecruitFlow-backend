package com.recruitflow.api.repositories;

import com.recruitflow.api.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface VagaRepository extends PagingAndSortingRepository<Vaga, Long>, JpaRepository<Vaga, Long>, JpaSpecificationExecutor<Vaga> {

}

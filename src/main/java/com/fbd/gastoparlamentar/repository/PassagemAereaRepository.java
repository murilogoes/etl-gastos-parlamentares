package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Partido;
import com.fbd.gastoparlamentar.model.PassagemAerea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemAereaRepository extends JpaRepository<PassagemAerea, Integer> {
}

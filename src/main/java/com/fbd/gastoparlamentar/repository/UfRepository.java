package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Partido;
import com.fbd.gastoparlamentar.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {
    Uf findBySigla(String sigla);

}

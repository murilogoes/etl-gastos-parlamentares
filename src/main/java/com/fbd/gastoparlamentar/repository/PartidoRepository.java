package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Legislatura;
import com.fbd.gastoparlamentar.model.Partido;
import com.fbd.gastoparlamentar.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
    Partido findBySigla(String sigla);
}

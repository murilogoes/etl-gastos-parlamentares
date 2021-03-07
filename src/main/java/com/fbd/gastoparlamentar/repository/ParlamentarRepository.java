package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Parlamentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParlamentarRepository extends JpaRepository<Parlamentar, Integer> {
    Parlamentar findByNome(String nome);

}

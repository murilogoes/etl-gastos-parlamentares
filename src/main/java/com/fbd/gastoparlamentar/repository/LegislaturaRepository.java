package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Legislatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegislaturaRepository extends JpaRepository<Legislatura, Integer> {
}

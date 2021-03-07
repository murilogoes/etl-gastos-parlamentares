package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Despesa;
import com.fbd.gastoparlamentar.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

}

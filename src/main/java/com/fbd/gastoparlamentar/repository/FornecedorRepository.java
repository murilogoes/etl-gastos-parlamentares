package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Fornecedor;
import com.fbd.gastoparlamentar.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    Fornecedor findByCnpj(String cnpj);

    @Query(value = "SELECT * FROM fornecedor f WHERE f.descricao = ?1 AND f.cnpj = ?2 LIMIT 1", nativeQuery = true)
    Fornecedor findByNomeCnpj(String descricao, String cnpj);

}

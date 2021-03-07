package com.fbd.gastoparlamentar.repository;

import com.fbd.gastoparlamentar.model.Subcota;
import com.fbd.gastoparlamentar.model.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcotaRepository extends JpaRepository<Subcota, Integer> {

}

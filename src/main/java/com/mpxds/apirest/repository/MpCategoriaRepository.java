package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apirest.model.MpCategoria;

@Repository
public interface MpCategoriaRepository extends JpaRepository<MpCategoria, Integer> {

}

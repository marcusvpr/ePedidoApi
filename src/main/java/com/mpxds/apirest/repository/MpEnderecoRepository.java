package com.mpxds.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpxds.apirest.model.MpEndereco;

@Repository
public interface MpEnderecoRepository extends JpaRepository<MpEndereco, Integer> {

}

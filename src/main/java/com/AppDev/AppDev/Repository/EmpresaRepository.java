package com.AppDev.AppDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppDev.AppDev.models.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	Empresa findById(long id);
	List<Empresa> findByNome(String nome);
	
	// Query para a busca
	@Query(value = "select u from Empresa u where u.nome like %?1%")
	List<Empresa>findByNomesEmpresa(String nome);
}
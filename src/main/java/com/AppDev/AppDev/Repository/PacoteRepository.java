package com.AppDev.AppDev.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppDev.AppDev.models.Pacote;

public interface PacoteRepository extends CrudRepository<Pacote, String> {
	
	Pacote findByCodigo(long codigo);
	List<Pacote>findByNome(String nome);
	
	// Query para a busca
		@Query(value = "select u from Pacote u where u.nome like %?1%")
		List<Pacote> findByNomesPacote(String nome);
	

}

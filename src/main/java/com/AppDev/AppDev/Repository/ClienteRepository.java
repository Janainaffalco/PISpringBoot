package com.AppDev.AppDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.AppDev.AppDev.models.Cliente;
import com.AppDev.AppDev.models.Pacote;


public interface ClienteRepository extends CrudRepository<Cliente, String> {

	Iterable<Cliente>findByPacote(Pacote pacote);
	
	Cliente findByCpf(String cpf);
	
	//deletar por id
	Cliente findById(long id);
	
	//Busca de Cliente
	@Query
	List<Cliente>findByNomeCliente(String nomeCliente);
	
	
	
}

package com.aircrop.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.aircrop.backend.model.Cliente;

public interface IClienteDao extends CrudRepository<Cliente,Long>{
	
	Cliente findByEmail(String email);
	Cliente findByNombreUsuario(String nombreUsuario);


}

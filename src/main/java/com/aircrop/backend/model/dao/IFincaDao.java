package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.model.Cultivo;
import com.aircrop.backend.model.Finca;

public interface IFincaDao extends CrudRepository<Finca,Long>{
	
	List<Finca> findByCultivo(Cultivo cultivo);

	
}

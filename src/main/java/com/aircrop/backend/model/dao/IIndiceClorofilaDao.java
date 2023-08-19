package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndiceClorofila;

public interface IIndiceClorofilaDao extends CrudRepository<IndiceClorofila, Long>{
	
	List<IndiceClorofila> findByFinca(Finca finca);

}

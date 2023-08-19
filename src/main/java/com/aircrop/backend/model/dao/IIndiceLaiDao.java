package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndiceLAI;

public interface IIndiceLaiDao extends CrudRepository<IndiceLAI,Long>{

	List<IndiceLAI> findByFinca(Finca finca);
}

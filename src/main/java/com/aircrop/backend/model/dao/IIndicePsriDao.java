package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndicePSRI;

public interface IIndicePsriDao extends CrudRepository<IndicePSRI, Long>{

	List<IndicePSRI> findByFinca(Finca finca);
}

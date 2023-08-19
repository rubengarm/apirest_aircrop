package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndiceNDVI;

public interface IIndiceNdviDao extends CrudRepository<IndiceNDVI, Long>{

	List<IndiceNDVI> findByFinca(Finca finca);
}

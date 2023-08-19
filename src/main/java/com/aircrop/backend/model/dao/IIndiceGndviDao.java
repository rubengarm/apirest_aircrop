package com.aircrop.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aircrop.backend.model.Finca;
import com.aircrop.backend.model.IndiceGNDVI;

public interface IIndiceGndviDao extends CrudRepository<IndiceGNDVI,Long>{

	List<IndiceGNDVI> findByFinca(Finca finca);
}

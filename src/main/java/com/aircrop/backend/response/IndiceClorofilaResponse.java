package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.IndiceClorofila;


public class IndiceClorofilaResponse {
	private List<IndiceClorofila> indiceClorofila;
	
	public List<IndiceClorofila> getIndiceClorofila(){
		return indiceClorofila;
	}
	
	public void setIndiceClorofila(List<IndiceClorofila> indiceClorofila) {
		this.indiceClorofila=indiceClorofila;
	}
}

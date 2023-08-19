package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.IndicePSRI;



public class IndicePsriResponse {
	
	private List<IndicePSRI> indicePSRI;
	
	public List<IndicePSRI> getIndicePsri(){
		return indicePSRI;
	}
	
	public void setIndicePsri(List<IndicePSRI> indicePSRI) {
		this.indicePSRI=indicePSRI;
	}
}

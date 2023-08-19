package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.IndiceLAI;



public class IndiceLaiResponse {
	
	private List<IndiceLAI> indiceLAI;
	
	public List<IndiceLAI> getIndiceLai(){
		return indiceLAI;
	}
	
	public void setIndiceLai(List<IndiceLAI> indiceLAI) {
		this.indiceLAI=indiceLAI;
	}
}

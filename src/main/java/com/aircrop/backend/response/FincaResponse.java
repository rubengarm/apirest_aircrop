package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.Finca;

public class FincaResponse {
	
	private List<Finca> finca;
	
	public List<Finca> getFinca(){
		return finca;
	}
	
	public void setFinca(List<Finca> finca) {
		this.finca=finca;
	}
}

package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.Cultivo;

public class CultivoResponse {
	
	private List<Cultivo> cultivo;
	
	public List<Cultivo> getCultivo(){
		return cultivo;
	}
	
	public void setCultivo(List<Cultivo> cultivo) {
		this.cultivo=cultivo;
	}
}

package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.IndiceGNDVI;

public class IndiceGndviResponse {
	
	private List<IndiceGNDVI> indiceGNDVI;
	
	public List<IndiceGNDVI> getIndiceGndvi(){
		return indiceGNDVI;
	}
	
	public void setIndiceGndvi(List<IndiceGNDVI> indiceGNDVI) {
		this.indiceGNDVI=indiceGNDVI;
	}
	
	

}

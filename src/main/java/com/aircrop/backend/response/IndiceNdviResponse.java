package com.aircrop.backend.response;

import java.util.List;

import com.aircrop.backend.model.IndiceNDVI;

public class IndiceNdviResponse {
	
	private List<IndiceNDVI> indiceNDVI;
	
	public List<IndiceNDVI> getIndiceNdvi(){
		return indiceNDVI;
	}
	
	public void setIndiceNdvi(List<IndiceNDVI> indiceNDVI) {
		this.indiceNDVI=indiceNDVI;
	}
	
}

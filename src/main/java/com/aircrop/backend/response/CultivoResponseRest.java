package com.aircrop.backend.response;

public class CultivoResponseRest extends ResponseRest{
	
	private CultivoResponse cultivoResponse = new CultivoResponse();
	
	public CultivoResponse getCultivoResponse() {
		return cultivoResponse;
	}
	
	public void setCultivoResponse(CultivoResponse cultivoResponse) {
		this.cultivoResponse=cultivoResponse;
	}

}

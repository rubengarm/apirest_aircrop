package com.aircrop.backend.response;

public class FincaResponseRest extends ResponseRest{
	
	private FincaResponse fincaResponse = new FincaResponse();
	
	public FincaResponse getFincaResponse() {
		return fincaResponse;
	}
	
	public void setFincaResponse(FincaResponse fincaResponse) {
		this.fincaResponse=fincaResponse;
	}
}

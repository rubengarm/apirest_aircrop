package com.aircrop.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Esta clase, es una clase de respuesta de nuestra API que nos va a permitir
 * añadir metadata, es decir, información adicional que puede ser de utilidad al cliente
 */
public class ResponseRest {

	/**
	 * Creamos un ArrayList de tipo HasMap para poder almacenar toda la metadata
	 * Además de la información que solicite el usuario, vamos a poder enviar metadatas informando
	 * al cliente si todo va bien, si hay un error, etc
	 */
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}
	
	
	/**
	 * 
	 * En este método añadimos información adicional al usuario
	 */

	public void setMetadata(String tipo, String codigo, String date) {

		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("tipo", tipo);
		mapa.put("codigo", codigo);
		mapa.put("dato", date);
		
		metadata.add(mapa);
		
	}
}
	
	

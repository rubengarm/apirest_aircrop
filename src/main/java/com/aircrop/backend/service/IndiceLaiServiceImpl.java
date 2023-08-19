package com.aircrop.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aircrop.backend.model.Finca;

import com.aircrop.backend.model.IndiceLAI;

import com.aircrop.backend.model.dao.IFincaDao;
import com.aircrop.backend.model.dao.IIndiceLaiDao;
import com.aircrop.backend.response.IndiceLaiResponseRest;

@Service
public class IndiceLaiServiceImpl implements IIndiceLaiService{
	
	private static final Logger log = LoggerFactory.getLogger(IndiceLaiServiceImpl.class);
	
	@Autowired
	private IIndiceLaiDao laiDao;
	
	@Autowired
	private IFincaDao fincaDao;
	
	@Override
	public ResponseEntity<IndiceLaiResponseRest> buscarIndiceLai() {
		log.info("Inicio método buscar indice LAI");
		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		
		try {
			
			
			List<IndiceLAI> indiceLAI = (List<IndiceLAI>) laiDao.findAll();
			response.getIndiceLaiResponse().setIndiceLai(indiceLAI);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los indices LAI");
			log.error("Error al consultar cultivos ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<IndiceLaiResponseRest> buscarPorId(Long id) {
		log.info("Inicio del método buscarPorId");

		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		List<IndiceLAI> list = new ArrayList<>();
		
		try {
			
			Optional<IndiceLAI> indiceLai = laiDao.findById(id);
			
			if(indiceLai.isPresent()) {//si existe la categoría
				list.add(indiceLai.get());
				response.setMetadata("Respuesta ok", "00", "Indice LAI encontrado");
				response.getIndiceLaiResponse().setIndiceLai(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los indice LAI");
				response.setMetadata("Respuesta nok", "-1", "Indice LAI no encontrado");
				return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los indices LAI");
			response.setMetadata("Respuesta nok","-1","Error al consultar el indice LAI");
			return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceLaiResponseRest> crear(IndiceLAI indiceLAI) {
		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		List<IndiceLAI> list = new ArrayList<>();
		
		try {
			
			IndiceLAI laiGuardado = laiDao.save(indiceLAI);
			
			if (laiGuardado != null) {
				list.add(laiGuardado);
				response.getIndiceLaiResponse().setIndiceLai(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el indice LAI");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el indice LAI");
				return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el indice LAI");
			response.setMetadata("Respuesta noK", "-1", "Error al consultar el indice LAI");
			return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceLaiResponseRest> actualizar(IndiceLAI indiceLAI, Long id) {
		log.info("Inicio del metodo actualizar indice LAI");
		
		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		List<IndiceLAI> list = new ArrayList<>();
		
		try {
			Optional<IndiceLAI> indiceBuscado = laiDao.findById(id);
			
			if(indiceBuscado.isPresent()) {
				indiceBuscado.get().setDate(indiceLAI.getDate());
				indiceBuscado.get().setFinca(indiceLAI.getFinca());
				indiceBuscado.get().setMin(indiceLAI.getMin());
				indiceBuscado.get().setMed(indiceLAI.getMed());
				indiceBuscado.get().setMax(indiceLAI.getMax());
				
				IndiceLAI indiceActualizar = laiDao.save(indiceBuscado.get());
				
				if(indiceActualizar != null) {
					list.add(indiceActualizar);
					response.getIndiceLaiResponse().setIndiceLai(list);
					response.setMetadata("Respuesta ok", "00", "Indice LAI actualizado correctamente");
				}else {
					log.error("Error al actualizar el indice LAI");
					response.setMetadata("Respuesta nok", "-1", "Indice LAI no actualizado");
					return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			}else {
				log.error("Error al actualizar el indice LAI");
				response.setMetadata("Respuesta nok", "-1", "Indice LAI no encontrado");
				return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e) {
			log.error("Error al actualizar el indice LAI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice LAI no actualizado");
			return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.OK);
		
		
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceLaiResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		
		try {
			
			//Eliminamos el registro
			laiDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Indice LAI eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar indice LAI", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Indice LAI no eliminado");
			return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	@Transactional
	public ResponseEntity<IndiceLaiResponseRest> buscarPorFinca(Long idFinca) {
		log.info("Inicio método buscar indice LAI por finca");
		
		IndiceLaiResponseRest response = new IndiceLaiResponseRest();
		
		try {
			
			Optional<Finca> finca = fincaDao.findById(idFinca);
			
			if(finca.isPresent()) {
				Finca fincaFound=new Finca();
				fincaFound.setId(finca.get().getId());
				fincaFound.setNombre(finca.get().getNombre());
				fincaFound.setCultivo(finca.get().getCultivo());
				fincaFound.setDireccion(finca.get().getDireccion());
				fincaFound.setMunicipio(finca.get().getMunicipio());
				fincaFound.setHectareas(finca.get().getHectareas());
				fincaFound.setProvincia(finca.get().getProvincia());
				
				List<IndiceLAI> indiceLai=(List<IndiceLAI>) laiDao.findByFinca(fincaFound);
				response.getIndiceLaiResponse().setIndiceLai(indiceLai);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			}else {
				log.error("Error al buscar la finca");
				response.setMetadata("Respuesta nok", "-1", "Finca no encontrada");
				return new ResponseEntity<IndiceLaiResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar indice LAI",e.getMessage());
			response.setMetadata("Respueta nok" , "-1", "Error al consultar indice lai");
			e.getStackTrace();
			return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<IndiceLaiResponseRest>(response,HttpStatus.OK);
	}
}

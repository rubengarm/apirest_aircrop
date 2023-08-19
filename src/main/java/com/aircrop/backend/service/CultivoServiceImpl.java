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

import com.aircrop.backend.model.Cliente;
import com.aircrop.backend.model.Cultivo;
import com.aircrop.backend.model.dao.IClienteDao;
import com.aircrop.backend.model.dao.ICultivoDao;
import com.aircrop.backend.response.CultivoResponseRest;


@Service
public class CultivoServiceImpl implements ICultivoService{
	
	private static final Logger log = LoggerFactory.getLogger(CultivoServiceImpl.class);
	
	@Autowired
	private ICultivoDao cultivoDao;
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public ResponseEntity<CultivoResponseRest> buscarCultivos() {
		log.info("Inicio método buscar cultivos");
		CultivoResponseRest response = new CultivoResponseRest();
		
		try {
			
			
			List<Cultivo> cultivo = (List<Cultivo>) cultivoDao.findAll();
			response.getCultivoResponse().setCultivo(cultivo);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los cultivos");
			log.error("Error al consultar cultivos ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CultivoResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CultivoResponseRest> buscarPorId(long id) {
		log.info("Inicio del método buscarPorId");

		CultivoResponseRest response = new CultivoResponseRest();
		List<Cultivo> list = new ArrayList<>();
		
		try {
			
			Optional<Cultivo> cultivo = cultivoDao.findById(id);
			
			if(cultivo.isPresent()) {//si existe la categoría
				list.add(cultivo.get());
				response.setMetadata("Respuesta ok", "00", "Cultivo encontrado");
				response.getCultivoResponse().setCultivo(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los cultivos");
				response.setMetadata("Respuesta nok", "-1", "Cultivo no encontrado");
				return new ResponseEntity<CultivoResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los cultivos");
			response.setMetadata("Respuesta nok","-1","Error al consultar el cultivo");
			return new ResponseEntity<CultivoResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CultivoResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<CultivoResponseRest> crear(Cultivo cultivo) {
	log.info("Inico del método crear categoría");
		
		CultivoResponseRest response = new CultivoResponseRest();
		List<Cultivo> list = new ArrayList<>();
		
		try {
			
			Cultivo cultivoGuardada = cultivoDao.save(cultivo);
			
			if (cultivoGuardada != null) {
				list.add(cultivoGuardada);
				response.getCultivoResponse().setCultivo(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el cultivo");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el cultivo");
				return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el cultivo");
			response.setMetadata("Respuesta noK", "-1", "Error al guardar el cultivo");
			return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CultivoResponseRest> actualizar(Cultivo cultivo, long id) {
		log.info("Inicio del método actualizar cultivo");
		
		CultivoResponseRest response = new CultivoResponseRest();
		List<Cultivo> list = new ArrayList<>();
		
		try {
		
			Optional<Cultivo> cultivoBuscado = cultivoDao.findById(id);
			
			if(cultivoBuscado.isPresent()) {
				
				cultivoBuscado.get().setNombre(cultivo.getNombre());
				cultivoBuscado.get().setTipo(cultivo.getTipo());
				
				
				Cultivo cultivoActualizar = cultivoDao.save(cultivoBuscado.get());//actualizando
				
				if (cultivoActualizar != null) {
					list.add(cultivoActualizar);
					response.getCultivoResponse().setCultivo(list);
					response.setMetadata("Respuesta Ok", "00", "Cultivo actualizado correctamente");
				}else {
					log.error("Error al actualizar el cultivo");
					response.setMetadata("Respuesta noK", "-1", "Cultivo no actualizado");
					return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			
			}else {
				log.error("Error al acutalizar el cultivo");
				response.setMetadata("Respuesta nok", "-1", "Cultivo no encontrado");
				return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			log.error("Error al actualizar cultivo", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cultivo no actualizado");
			return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CultivoResponseRest> eliminar(long id) {
	log.info("Inicio del método eliminar");
		
		CultivoResponseRest response = new CultivoResponseRest();
		
		try {
			
			//Eliminamos el registro
			cultivoDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Cultivo eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar el clinte", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cultivo no eliminado");
			return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.OK);	
	}

	@Override
	@Transactional
	public ResponseEntity<CultivoResponseRest> findByCliente(Long idCliente) {
		log.info("Inicio método buscar cultivos");
		CultivoResponseRest response = new CultivoResponseRest();
		
		
		
		try {
			
			Optional<Cliente> cliente= clienteDao.findById(idCliente); //primero buscamos el cliente
			
			if(cliente.isPresent()) { // si lo encontramos pasamos de optional a cliente para poder usar el metodo de buscar por cliente
				Cliente clienteFound= new Cliente();
				clienteFound.setId(cliente.get().getId());
				clienteFound.setNombre(cliente.get().getNombre());
				clienteFound.setEmail(cliente.get().getEmail());
				clienteFound.setCif(cliente.get().getCif());
				clienteFound.setTelefono(cliente.get().getTelefono());
				
				List<Cultivo> cultivo = (List<Cultivo>) cultivoDao.findByCliente(clienteFound);//buscamos los cultivos de ese cliente
				response.getCultivoResponse().setCultivo(cultivo);
				
				response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
			}else {
				log.error("Error al buscar cliente"); //si no existe el cliente
				response.setMetadata("Respuesta nok", "-1", "Cliente no encontrado");
				return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
			
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los cultivos");
			log.error("Error al consultar cultivos ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CultivoResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<CultivoResponseRest>(response, HttpStatus.OK);
	}
	

}

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
import com.aircrop.backend.model.dao.IClienteDao;
import com.aircrop.backend.response.ClienteResponseRest;

@Service
public class ClienteServiceImpl implements IClienteService{
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private IClienteDao clienteDao;
	
	

	@Override
	public ResponseEntity<ClienteResponseRest> buscarClientes() {
		log.info("Inicio método buscar clientes");
		ClienteResponseRest response = new ClienteResponseRest();
		
		try {
			
			
			List<Cliente> cliente = (List<Cliente>) clienteDao.findAll();
			response.getClienteResponse().setCliente(cliente);
			
			response.setMetadata("Respuesta ok", "00", "Respuesta existosa");
		}catch (Exception e){
			
			response.setMetadata("Respuesta noK", "-1", "Error al consultar los clientes");
			log.error("Error al consultar clientes ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);

	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ClienteResponseRest> buscarPorId(Long id) {

		log.info("Inicio del método buscarPorId");

		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		
		try {
			
			Optional<Cliente> cliente = clienteDao.findById(id);
			
			if(cliente.isPresent()) {//si existe la categoría
				list.add(cliente.get());
				response.setMetadata("Respuesta ok", "00", "Cliente encontrado");
				response.getClienteResponse().setCliente(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los clientes");
				response.setMetadata("Respuesta nok", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los clientes");
			response.setMetadata("Respuesta nok","-1","Error al consultar cliente");
			return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> crear(Cliente cliente) {
		log.info("Inico del método crear cliente");
		
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		
		try {
			
			Cliente clienteGuardada = clienteDao.save(cliente);
			
			if (clienteGuardada != null) {
				list.add(clienteGuardada);
				response.getClienteResponse().setCliente(list);
				response.setMetadata("Respuesta OK", "00", "Respuesta guardada correctamente");
			}else {
				log.error("Error al grabar el cliente");
				response.setMetadata("Respuesta noK", "-1", "Error al grabar el cliente");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {
			log.error("Error al consultar el cliente");
			response.setMetadata("Respuesta noK", "-1", "Error al grabar el cliente");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
		
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> actualizar(Cliente cliente, Long id) {
		log.info("Inicio del método actualizar cliente");
		
		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		
		try {
		
			Optional<Cliente> clienteBuscado = clienteDao.findById(id);
			
			if(clienteBuscado.isPresent()) {
				
				clienteBuscado.get().setNombre(cliente.getNombre());
				clienteBuscado.get().setCif(cliente.getCif());
				clienteBuscado.get().setEmail(cliente.getEmail());
				clienteBuscado.get().setTelefono(cliente.getTelefono());
				
				Cliente clienteActualizar = clienteDao.save(clienteBuscado.get());//actualizando
				
				if (clienteActualizar != null) {
					list.add(clienteActualizar);
					response.getClienteResponse().setCliente(list);
					response.setMetadata("Respuesta Ok", "00", "Cliente actualizado correctamente");
				}else {
					log.error("Error al actualizar el cliente");
					response.setMetadata("Respuesta noK", "-1", "Cliente no actualizado");
					return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			
			}else {
				log.error("Error al acutalizar el cliente");
				response.setMetadata("Respuesta nok", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			log.error("Error al actualizar cliente", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cliente no actualizado");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> eliminar(Long id) {
		log.info("Inicio del método eliminar");
		
		ClienteResponseRest response = new ClienteResponseRest();
		
		try {
			
			//Eliminamos el registro
			clienteDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Cliente eliminado correctamente");
		}catch(Exception e) {
			log.error("Error al eliminar el clinte", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta nok", "-1", "Cliente no eliminado");
			return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);	
	}

	

	@Override
	@Transactional
	public ResponseEntity<ClienteResponseRest> buscarPorNombreUsuario(String nombreUsuario) {
		log.info("Inicio del método buscarPorNombreUsuario");

		ClienteResponseRest response = new ClienteResponseRest();
		List<Cliente> list = new ArrayList<>();
		
		try {
			
			Cliente cliente = clienteDao.findByNombreUsuario(nombreUsuario);
			
			if(cliente!=null) {//si existe la categoría
				list.add(cliente);
				response.setMetadata("Respuesta ok", "00", "Cliente encontrado");
				response.getClienteResponse().setCliente(list);
			}else {//si no existe la categoría
				log.error("Error al consultar los clientes");
				response.setMetadata("Respuesta nok", "-1", "Cliente no encontrado");
				return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {
			
			log.error("Error al consultar los clientes");
			response.setMetadata("Respuesta nok","-1","Error al consultar cliente");
			return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<ClienteResponseRest>(response,HttpStatus.OK);//respuesta exitosa
	}
	
	
}

package com.aircrop.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 806335511719798425L;
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(name="NOMBRE")
		private String nombre;
		
		@Column(name="NOMBREDEUSUARIO", unique=true, nullable=false)
		private String nombreUsuario;
		
		@Column(name="CIF", nullable=false)
		private String cif;
		
		@Column(name="EMAIL", nullable=false)
		private String email;
		
		@Column(name="TELEFONO")
		private String telefono;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCif() {
			return cif;
		}

		public void setCif(String cif) {
			this.cif = cif;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}

		public String getNombreUsuario() {
			return nombreUsuario;
		}

		public void setNombreDeUsuario(String nombreDeUsuario) {
			this.nombreUsuario = nombreDeUsuario;
		}
		
		
}

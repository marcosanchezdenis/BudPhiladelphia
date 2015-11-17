package com.wildfi.budPhiladelphiadb;

public class User {
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
//	private String USER_ID;
	private String nombre;
	
	private String cedula;
	private String email;
	private String celular;

	private String nacimiento;



	
	public User(String nombre,			
			String cedula,
			String email,
			String celular,			
			String nacimiento) {
		super();
		this.nombre = nombre;
		
		this.cedula = cedula;
		this.email = email;
		this.celular = celular;
		
		this.nacimiento = nacimiento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	

	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;		
	}
	
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	

	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	

}
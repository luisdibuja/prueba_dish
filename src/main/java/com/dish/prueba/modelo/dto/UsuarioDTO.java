package com.dish.prueba.modelo.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	private Long id;

	private String nombre;

	private Integer edad;

	private double salario;

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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UsuarioDTO usuarioDTO = (UsuarioDTO) o;

		if (Double.compare(usuarioDTO.salario, salario) != 0) return false;
		if (id != null ? !id.equals(usuarioDTO.id) : usuarioDTO.id != null) return false;
		if (nombre != null ? !nombre.equals(usuarioDTO.nombre) : usuarioDTO.nombre != null) return false;
		return edad != null ? edad.equals(usuarioDTO.edad) : usuarioDTO.edad == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id != null ? id.hashCode() : 0;
		result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
		result = 31 * result + (edad != null ? edad.hashCode() : 0);
		temp = Double.doubleToLongBits(salario);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", edad=" + edad
				+ ", salario=" + salario + "]";
	}


}

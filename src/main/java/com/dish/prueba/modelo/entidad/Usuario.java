package com.dish.prueba.modelo.entidad;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NOMBRE", nullable=false)
	private String nombre;

	@Column(name="EDAD", nullable=false)
	private Integer edad;

	@Column(name="SALARIO", nullable=false)
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

		Usuario usuario = (Usuario) o;

		if (Double.compare(usuario.salario, salario) != 0) return false;
		if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
		if (nombre != null ? !nombre.equals(usuario.nombre) : usuario.nombre != null) return false;
		return edad != null ? edad.equals(usuario.edad) : usuario.edad == null;
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

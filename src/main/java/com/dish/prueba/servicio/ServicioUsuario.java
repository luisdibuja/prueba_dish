package com.dish.prueba.servicio;


import com.dish.prueba.modelo.dto.UsuarioDTO;

import java.util.List;

public interface ServicioUsuario {

	UsuarioDTO buscarPorId(Long id);

	UsuarioDTO buscarPorNombre(String name);

	void guardarUsuario(UsuarioDTO usuario);

	void actualizarUsuario(UsuarioDTO usuario);

	void borrarUsuarioPorId(Long id);

	void borrarTodosLosUsuarios();

	List<UsuarioDTO> encontrarTodosLosUsuarios();

	boolean isUsuarioExiste(UsuarioDTO usuario);
}
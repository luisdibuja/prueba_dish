package com.dish.prueba.servicio;

import com.dish.prueba.modelo.dto.UsuarioDTO;
import com.dish.prueba.modelo.entidad.Usuario;
import com.dish.prueba.repositorios.RepositorioUsuario;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private final RepositorioUsuario repositorioUsuario;

	private final MapperFacade mapperFacade;

	private ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario,
								MapperFacade mapperFacade) {
	    this.repositorioUsuario = repositorioUsuario;
	    this.mapperFacade = mapperFacade;
    }

	public UsuarioDTO buscarPorId(Long id) {
		final Usuario usuario = repositorioUsuario.findOne(id);
		return mapperFacade.map(usuario, UsuarioDTO.class);
	}

	public UsuarioDTO buscarPorNombre(String name) {
		final Usuario usuario = repositorioUsuario.findByNombre(name);
		return mapperFacade.map(usuario, UsuarioDTO.class);
	}

	public void guardarUsuario(UsuarioDTO usuarioDTO) {
		final Usuario usuario = mapperFacade.map(usuarioDTO, Usuario.class);
		repositorioUsuario.save(usuario);
	}

	public void actualizarUsuario(UsuarioDTO usuarioDTO){
		guardarUsuario(usuarioDTO);
	}

	public void borrarUsuarioPorId(Long id){
		repositorioUsuario.delete(id);
	}

	public void borrarTodosLosUsuarios(){
		repositorioUsuario.deleteAll();
	}

	public List<UsuarioDTO> encontrarTodosLosUsuarios(){
		List<Usuario> usuarios = repositorioUsuario.findAll();
		return mapperFacade.mapAsList(usuarios, UsuarioDTO.class);
	}

	public boolean isUsuarioExiste(UsuarioDTO usuarioDTO) {
		final Usuario usuario = mapperFacade.map(usuarioDTO, Usuario.class);
		return buscarPorNombre(usuario.getNombre()) != null;
	}

}

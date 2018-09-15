package com.dish.prueba.controlador;

import com.dish.prueba.modelo.dto.UsuarioDTO;
import com.dish.prueba.modelo.entidad.Usuario;
import com.dish.prueba.servicio.ServicioUsuario;
import com.dish.prueba.util.ErrorPersonalizado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ControladorRest {

	private static final Logger logger = LoggerFactory.getLogger(ControladorRest.class);

	private final ServicioUsuario servicioUsuario; //Servicio que hará toda la obtención de datos

	public ControladorRest(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

	// -------------------Devuelve todos los usuarios---------------------------------------------

	@GetMapping(value = "/usuario")
	public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios() {
		List<UsuarioDTO> usuarios = servicioUsuario.encontrarTodosLosUsuarios();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
			// También es posible regresar HttpStatus.NOT_FOUND
		}
		return ResponseEntity.ok(usuarios);
	}

	// -------------------Devuelve un solo UsuarioDTO------------------------------------------

	@GetMapping(value = "/usuario/{id}")
	public ResponseEntity<?> obtenerUsuario(@PathVariable("id") long id) {
		logger.info("Buscando UsuarioDTO con id {}", id);
		UsuarioDTO usuario = servicioUsuario.buscarPorId(id);
		if (usuario == null) {
			logger.error("UsuarioDTO con id {} no encontrado.", id);
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorPersonalizado("UsuarioDTO con id " + id
					+ " no encontrado"));

		}
		return ResponseEntity.ok(usuario);
	}

	// -------------------Crea un UsuarioDTO-------------------------------------------

	@PostMapping(value = "/usuario")
	public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO usuario, UriComponentsBuilder ucBuilder) {
		logger.info("Creando UsuarioDTO : {}", usuario);

		if (servicioUsuario.isUsuarioExiste(usuario)) {
			logger.error("No se pudo crear. UsuarioDTO with name {} already exist", usuario.getNombre());
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body(new ErrorPersonalizado("No fue posible crear un usuario con nombre "
                            + usuario.getNombre() + " ya existe."));
		}
		servicioUsuario.guardarUsuario(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/usuario/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// ------------------- Actualiza un UsuarioDTO ------------------------------------------------

	@PutMapping(value = "/usuario/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable("id") long id, @RequestBody UsuarioDTO usuario) {
		logger.info("Actualizando UsuarioDTO con id {}", id);

		UsuarioDTO usuarioActual = servicioUsuario.buscarPorId(id);

		if (usuarioActual == null) {
			logger.error("No se pudo actualizar. UsuarioDTO con id {} not encontrado.", id);
			return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorPersonalizado("No se pudo actualizar. UsuarioDTO con id "
                            + id + " no encontrado."));
		}

		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setEdad(usuario.getEdad());
		usuarioActual.setSalario(usuario.getSalario());

		servicioUsuario.actualizarUsuario(usuarioActual);
		return ResponseEntity.ok(usuarioActual);
	}

	// ------------------- Borra un UsuarioDTO-----------------------------------------

	@DeleteMapping(value = "/usuario/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable("id") long id) {
		logger.info("Buscando & Borrando UsuarioDTO con id {}", id);

		UsuarioDTO usuario = servicioUsuario.buscarPorId(id);
		if (usuario == null) {
			logger.error("No se pudo borrar. UsuarioDTO con id {} no encontrado.", id);
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ErrorPersonalizado("No se pudo borrar. UsuarioDTO con id "
                            + id + " no encontrado."));
		}
		servicioUsuario.borrarUsuarioPorId(id);
		return ResponseEntity.noContent().build();
	}

	// ------------------- Borra todos los Usuarios-----------------------------

	@DeleteMapping(value = "/usuario/")
	public ResponseEntity<Usuario> borrarTodosLosUsuarios() {
		logger.info("Borrando todos los usuarios");

		servicioUsuario.borrarTodosLosUsuarios();
		return ResponseEntity.noContent().build();
	}

}
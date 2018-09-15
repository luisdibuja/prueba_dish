package com.dish.prueba.repositorios;

import com.dish.prueba.modelo.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);

}

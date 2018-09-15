package com.dish.prueba.configuracion;

import com.dish.prueba.modelo.dto.UsuarioDTO;
import com.dish.prueba.modelo.entidad.Usuario;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionMapper {

    @Bean
    public MapperFactory mapperFactory() {
        final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Usuario.class, UsuarioDTO.class);
        return mapperFactory;
    }

    @Bean
    public MapperFacade mapperFacade(MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }

}

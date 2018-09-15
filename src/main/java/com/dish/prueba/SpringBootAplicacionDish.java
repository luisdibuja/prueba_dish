package com.dish.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.dish.prueba.configuracion.ConfiguracionJPA;


@Import(ConfiguracionJPA.class)
@SpringBootApplication(scanBasePackages={"com.dish.prueba"})
public class SpringBootAplicacionDish {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAplicacionDish.class, args);

	}
}

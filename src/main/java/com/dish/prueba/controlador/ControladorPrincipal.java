package com.dish.prueba.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorPrincipal {

	@RequestMapping("/")
	public String inicio(ModelMap modal) {
		modal.addAttribute("titulo","Prueba Dish");
		return "index";
	}

	@RequestMapping("/parciales/{pagina}")
	public String partialHandler(@PathVariable("pagina") final String page) {
		return page;
	}

}

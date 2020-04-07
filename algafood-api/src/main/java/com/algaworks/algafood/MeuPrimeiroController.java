package com.algaworks.algafood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;


@Controller
public class MeuPrimeiroController {
	
	@Autowired
	private AtivacaoClienteService ativacaoClienteService;
	
//	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
//		this.ativacaoClienteService = ativacaoClienteService;
//	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		ativacaoClienteService.ativar(new Cliente("Diego", "diegobraun2000@gmail.com", "1212121212"));
		return "hello";
	}
}

package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Profile("prod")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties notificadorProperties;
	
	private String host;
	
	private Integer porta;
	
	public NotificadorEmail() {
		System.out.println("Notificador de PROD");
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		host = notificadorProperties.getHostServidor();
		porta = notificadorProperties.getPortaServidor();
		System.out.println("Host: " + host + " Porta: " + porta);
		System.out.printf("Notificando %s através do email %s:  %s\n", 
				cliente.getNome(),cliente.getEmail(),mensagem);		
	}
	
}

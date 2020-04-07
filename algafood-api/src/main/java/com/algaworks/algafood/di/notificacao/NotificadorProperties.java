package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="notificador.email")
public class NotificadorProperties {
	private String hostServidor;
	private Integer portaServidor;
	
	public String getHostServidor() {
		return hostServidor;
	}
	public Integer getPortaServidor() {
		return portaServidor;
	}
	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}
	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
	
	
	
}

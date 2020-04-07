package com.algaworks.algafood.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.di.notificacao.NotificadorEmail;

//@Configuration
public class NotificacaoConfig {
	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificadorEmail = new NotificadorEmail();
		return notificadorEmail;
	}
}

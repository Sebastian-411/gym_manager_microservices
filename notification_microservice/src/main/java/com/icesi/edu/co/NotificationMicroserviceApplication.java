package com.icesi.edu.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.icesi.edu.co.service.RecuperacionService;

@SpringBootApplication
public class NotificationMicroserviceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(NotificationMicroserviceApplication.class, args);
		RecuperacionService recuperacionService = applicationContext.getBean(RecuperacionService.class);
		recuperacionService.iniciarProcesamiento();
	}
}

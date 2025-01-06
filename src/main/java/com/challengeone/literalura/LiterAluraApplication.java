package com.challengeone.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.challengeone.literalura.presentacion.MenuPrincipal;

@SpringBootApplication
public class LiterAluraApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LiterAluraApplication.class, args);

		MenuPrincipal menuPrincipal = context.getBean(MenuPrincipal.class);

		menuPrincipal.mostrarMenu();
	}
}

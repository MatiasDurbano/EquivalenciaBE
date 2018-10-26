package com.equialencia.equivalenciaBE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.equivalencia.equivalenciaBE.Controller",
		"com.equivalencia.equivalenciaBE.Service"})
@EntityScan({"com.equivalencia.equivalenciaBE.Model","com.equivalencia.equivalenciaBE.Model.TablasDb",
	"com.equivalencia.equivalenciaBE.Model.TablasIntermediasDb"})
@EnableJpaRepositories("com.equivalencia.equivalenciaBE.dao")
public class EquivalenciaBe1Application {

	public static void main(String[] args) {
		SpringApplication.run(EquivalenciaBe1Application.class, args);
	}
}

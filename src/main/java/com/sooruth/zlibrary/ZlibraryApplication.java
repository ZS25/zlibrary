package com.sooruth.zlibrary;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition( //http://localhost:8095/swagger-ui/index.html
		info = @Info(
				title = "Zlibrary application",
				version = "0.0.1-SNAPSHOT",
				description = "This project is made as the first microservice in the Zuniversity project",
				termsOfService = "sooruth",
				contact = @Contact(
						name = "Zulfekar",
						email = "https://www.linkedin.com/in/zulfekarsooruth"
				),
				license = @License(
						name = "License",
						url = "https://github.com/ZS25"
				)
		)
)
public class ZlibraryApplication {
	private static final Logger LOG = LoggerFactory.getLogger(ZlibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZlibraryApplication.class, args);
		LOG.info("Welcome to ZLibrary Microservice Application!");
	}
}

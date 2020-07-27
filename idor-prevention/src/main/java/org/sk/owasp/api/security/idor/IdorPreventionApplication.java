package org.sk.owasp.api.security.idor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class IdorPreventionApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdorPreventionApplication.class, args);
	}

}

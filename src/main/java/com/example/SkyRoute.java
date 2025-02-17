package com.example;

import com.example.config.OpenAPIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OpenAPIConfig.class)
public class SkyRoute {

	public static void main(String[] args) {
		SpringApplication.run(SkyRoute.class, args);
	}

}

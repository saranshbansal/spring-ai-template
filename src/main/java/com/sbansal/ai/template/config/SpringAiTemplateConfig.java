package com.sbansal.ai.template.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAiTemplateConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Spring AI Template API")
						.version("1.0")
						.description("API documentation for Spring AI Template")
						.contact(new Contact()
								.name("Saransh Bansal")
								.email("kratos.saransh@gmail.com")
						)
				);
	}
}

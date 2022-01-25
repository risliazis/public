package com.example.employee;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
//@EnableWebMvc

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 @Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("public")
	              .pathsToMatch("/**")
	              .build();
	  }
	 
	 @Bean
	    public OpenAPI openAPI() {
	        return new OpenAPI()
	                .components(new Components())
	                .info(new Info()
	                		.title("EMPLOYEE REST API")
	                		.description("REST API for EMPLOYEE")
	                		.version("1.0")
	                		.termsOfService("Terms of service")
	                		.license(new License()
	                				.name("Apache License Version 2.0")
	                				.url("https://www.apache.org/licenses/LICENSE-2.0"))
	                		.contact(new Contact()
	                				.name("Risli Azis")
	                				.email("risli.azis@gmail.com"))
	                );
	    }

}

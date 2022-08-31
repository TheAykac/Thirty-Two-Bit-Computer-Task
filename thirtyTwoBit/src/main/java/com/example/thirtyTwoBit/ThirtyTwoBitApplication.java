package com.example.thirtyTwoBit;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ThirtyTwoBitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirtyTwoBitApplication.class, args);
	}

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.thirtyTwoBit"))
                .build();
    }
 
 @Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}

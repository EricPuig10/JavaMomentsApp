package com.epapps.moments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MomentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentsApplication.class, args);
	}
/*	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4400","http://192.168.1.100:4000")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
	}*/
}

package com.skilldistillery.cheyenne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CheyennePlbgApplication extends SpringBootServletInitializer {
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(CheyennePlbgApplication.class);
	  }

	public static void main(String[] args) {
		SpringApplication.run(CheyennePlbgApplication.class, args);
	}

}

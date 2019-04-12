package com.gavr.vrlive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class VrliveApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(VrliveApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VrliveApplication.class);
	}

}

//@SpringBootApplication
//public class VrliveApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(VrliveApplication.class, args);
//	}
//
//
//}

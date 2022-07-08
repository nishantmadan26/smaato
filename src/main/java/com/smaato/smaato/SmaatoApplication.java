package com.smaato.smaato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SmaatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmaatoApplication.class, args);
	}

}

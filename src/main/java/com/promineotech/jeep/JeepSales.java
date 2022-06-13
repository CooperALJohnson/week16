package com.promineotech.jeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class JeepSales {

	public static void main(String[] args) {
		SpringApplication.run(JeepSales.class, args);
	}

}

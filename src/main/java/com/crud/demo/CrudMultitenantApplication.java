package com.crud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CrudMultitenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudMultitenantApplication.class, args);
	}

}

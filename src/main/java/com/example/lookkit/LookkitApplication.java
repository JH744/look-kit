package com.example.lookkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LookkitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookkitApplication.class, args);
	}

}

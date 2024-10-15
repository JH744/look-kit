package com.example.lookkit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.example.lookkit")
public class LookkitApplication {
	public static void main(String[] args) {
		SpringApplication.run(LookkitApplication.class, args);
	}
}

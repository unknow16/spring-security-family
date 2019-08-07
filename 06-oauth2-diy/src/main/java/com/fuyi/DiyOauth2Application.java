package com.fuyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fuyi.mapper")
public class DiyOauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(DiyOauth2Application.class, args);
	}
}

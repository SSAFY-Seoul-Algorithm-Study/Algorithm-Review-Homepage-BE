package com.Seoul5.Algo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = "com.Seoul5.Algo.model.dao")
@EnableSwagger2
public class Seoul5AlgoApplication implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(Seoul5AlgoApplication.class, args);
	}

}
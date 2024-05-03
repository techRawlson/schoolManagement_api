package com.ClassApi.Class;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassApplication.class, args);
		System.out.println("hello class");
	}

}

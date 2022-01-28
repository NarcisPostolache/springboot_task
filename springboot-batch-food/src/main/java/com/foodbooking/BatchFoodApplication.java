package com.foodbooking;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@EnableEurekaClient

public class BatchFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchFoodApplication.class);
	}

}

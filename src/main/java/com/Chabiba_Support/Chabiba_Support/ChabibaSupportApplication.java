package com.Chabiba_Support.Chabiba_Support;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.Chabiba_Support.Chabiba_Support.config.SecurityConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class ChabibaSupportApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChabibaSupportApplication.class, args);
	}

}

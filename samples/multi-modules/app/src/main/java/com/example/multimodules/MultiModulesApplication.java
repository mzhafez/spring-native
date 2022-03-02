package com.example.multimodules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.SerializationHint;

@SpringBootApplication
@SerializationHint (types = { java.lang.Integer.class, java.lang.String.class, java.lang.Number.class })
public class MultiModulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiModulesApplication.class, args);
	}

}

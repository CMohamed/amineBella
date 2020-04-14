package com.amineBella.demo;

import com.amineBella.demo.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static List<User> users = new ArrayList<>();
	public static void main(String[] args) {



		SpringApplication.run(DemoApplication.class, args);
	}

}

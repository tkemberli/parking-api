package com.tk.parkingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApiApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		return args -> {
//			userRepository.save(new User("admin",passwordEncoder.encode("admin"),"ADMIN"));
//		};
//	}
}

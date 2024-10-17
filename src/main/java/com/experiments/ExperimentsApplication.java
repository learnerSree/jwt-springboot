package com.experiments;

import com.experiments.entity.User;
import com.experiments.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
public class ExperimentsApplication {

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void initUsers(){

		List<User> users = Stream.of(new User("sreejesh", "$2a$12$1gzHzoVtkFMjvsuOKoM.PuQ.jzyO58KNslwnx/nnaXSRrHtqLP7IS", "sreejesh@gmail.com")).collect(Collectors.toList());

		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExperimentsApplication.class, args);
	}

}

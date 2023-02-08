package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MaximaJavaTransactionalApplication implements CommandLineRunner {
	@Autowired
	private CatService service;
	public static void main(String[] args) {

		SpringApplication.run(MaximaJavaTransactionalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello, Transactional");

		service.printAllCats();

		Cat murka = new Cat("Мурка",4);
		Cat tutankhmun = new Cat("Тутанхамон", 8);

		try {
			service.saveSomeCats(List.of(murka, tutankhmun));
		}
		catch (Exception e){
			System.out.println("Кот сломался");
		}

		System.out.println("После добавления:");
		service.printAllCats();
	}
}

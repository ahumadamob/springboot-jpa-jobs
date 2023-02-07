package com.ahumadamob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahumadamob.model.Category;
import com.ahumadamob.repository.CategoryRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(repo);
		saveCategory();
		
	}
	
	private void saveCategory() {
		Category category = new Category();
		category.setName("Finanzas");
		category.setDescription("Categoría relacionada con las finanzas y la contabilidad");
		repo.save(category);
		System.out.println(category);
	}

}

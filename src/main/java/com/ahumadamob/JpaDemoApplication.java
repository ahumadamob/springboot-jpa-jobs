package com.ahumadamob;

import java.util.Optional;

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
		//saveCategory();
		//getCategoryById();
		//updateCategory();
		//deleteCategory();
		countCategories();
		
	}
	
	private void saveCategory() {
		Category category = new Category();
		category.setName("Finanzas");
		category.setDescription("Categoría relacionada con las finanzas y la contabilidad");
		repo.save(category);
		System.out.println(category);
	}
	
	private void getCategoryById() {
		Optional<Category> opt = repo.findById(2);
		if(opt.isPresent()) {
			System.out.println(opt.get());
		}else {
			System.out.println("Objeto no encontrado");
		}
	}
	
	private void updateCategory() {
		Optional<Category> opt = repo.findById(1);
		if(opt.isPresent()) {
			Category categoryUpdate = opt.get();
			categoryUpdate.setName("Ingeniería de Software");
			categoryUpdate.setDescription("Desarrollo de Sistemas");
			repo.save(categoryUpdate);
		}else {
			System.out.println("Objeto no encontrado");
		}
	}
	
	private void deleteCategory() {
		int idCategoria = 1;
		repo.deleteById(idCategoria);
		//TODO: Acá hay que aplicar try catch porque si eliminamos uno que no existe da una excepción
	}	
	
	private void countCategories() {
		Long count = repo.count();
		System.out.println("Total categorias: " + count);
	}

}

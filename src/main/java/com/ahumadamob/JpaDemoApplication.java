package com.ahumadamob;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
		//deleteAll();
		//countCategories();
		//findAllById();
		//findAllCategories();
		//existCategoryById();
		//saveAllCategories();
		//findAllCategoriesJPA();
		//deleteAllInBatch();
		//findAllCategoriesOrderByJPA();
		findAllCategoriesPagination();
		
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
	
	private void deleteAll() {
		repo.deleteAll();
	}
	
	private void findAllCategoriesById() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		repo.findAllById(ids);
		Iterable<Category> categories = repo.findAllById(ids);
		for(Category elm : categories) {
			System.out.println(elm);
		}
	}
	
	private void findAllCategories() {
		Iterable<Category> categories =	repo.findAll();
		for(Category elm : categories) {
			System.out.println(elm);
		}
	}
	
	private void existCategoryById() {
		boolean exists = repo.existsById(99);
		System.out.println("La categoría existe? " + exists);
		
	}
	
	private void saveAllCategories() {
		
		repo.saveAll(getCategoryList());
	}
	
	private List<Category> getCategoryList(){
		List<Category> categories = new LinkedList<Category>();
		
		Category cat1 = new Category();
		cat1.setName("Bonus Track 1");
		cat1.setDescription("Categoría añadida 1");
		
		Category cat2 = new Category();
		cat2.setName("Bonus Track 2");
		cat2.setDescription("Categoría añadida 2");
		
		Category cat3 = new Category();
		cat3.setName("Bonus Track 3");
		cat3.setDescription("Categoría añadida 3");	
		
		categories.add(cat1);
		categories.add(cat2);
		categories.add(cat3);
		
		return categories;
	}
	
	private void findAllCategoriesJPA() {
		List<Category> categories =  repo.findAll();
		for(Category elm: categories) {
			System.out.println(elm.getId() + " " + elm.getName());
		}
	}
	
	private void deleteAllInBatch() {
		repo.deleteAllInBatch();
	}
	
	private void findAllCategoriesOrderByJPA() {
		List<Category> categories =  repo.findAll(Sort.by("name").descending());
		for(Category elm: categories) {
			System.out.println(elm.getId() + " " + elm.getName());
		}		
	}
	
	private void findAllCategoriesPagination() {
		Page<Category> page = repo.findAll(PageRequest.of(2, 5));
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total páginas: " + page.getTotalPages());
		for(Category elm: page) {
			System.out.println(elm.getId() + " " + elm.getName());
		}
	}

}

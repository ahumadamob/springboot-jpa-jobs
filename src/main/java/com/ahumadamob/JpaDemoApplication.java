package com.ahumadamob;

import java.util.Date;
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
import com.ahumadamob.model.Job;
import com.ahumadamob.model.Profile;
import com.ahumadamob.model.User;
import com.ahumadamob.repository.CategoryRepository;
import com.ahumadamob.repository.JobRepository;
import com.ahumadamob.repository.ProfileRepository;
import com.ahumadamob.repository.UserRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryR;
	
	@Autowired
	private JobRepository jobR;
	
	@Autowired
	private UserRepository userR;
	
	@Autowired
	private ProfileRepository profileR;
	
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
		//findAllCategoriesPaginationOrder();
		//findAllJobs();
		//saveJob();
		//createProfilesApp();
		//createUserManyProfiles();
		findUser();
	}
	
	private void saveCategory() {
		Category category = new Category();
		category.setName("Finanzas");
		category.setDescription("Categoría relacionada con las finanzas y la contabilidad");
		categoryR.save(category);
		System.out.println(category);
	}
	
	private void getCategoryById() {
		Optional<Category> opt = categoryR.findById(2);
		if(opt.isPresent()) {
			System.out.println(opt.get());
		}else {
			System.out.println("Objeto no encontrado");
		}
	}
	
	private void updateCategory() {
		Optional<Category> opt = categoryR.findById(1);
		if(opt.isPresent()) {
			Category categoryUpdate = opt.get();
			categoryUpdate.setName("Ingeniería de Software");
			categoryUpdate.setDescription("Desarrollo de Sistemas");
			categoryR.save(categoryUpdate);
		}else {
			System.out.println("Objeto no encontrado");
		}
	}
	
	private void deleteCategory() {
		int idCategoria = 1;
		categoryR.deleteById(idCategoria);
		//TODO: Acá hay que aplicar try catch porque si eliminamos uno que no existe da una excepción
	}	
	
	private void countCategories() {
		Long count = categoryR.count();
		System.out.println("Total categorias: " + count);
	}
	
	private void deleteAll() {
		categoryR.deleteAll();
	}
	
	private void findAllCategoriesById() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		categoryR.findAllById(ids);
		Iterable<Category> categories = categoryR.findAllById(ids);
		for(Category elm : categories) {
			System.out.println(elm);
		}
	}
	
	private void findAllCategories() {
		Iterable<Category> categories =	categoryR.findAll();
		for(Category elm : categories) {
			System.out.println(elm);
		}
	}
	
	private void existCategoryById() {
		boolean exists = categoryR.existsById(99);
		System.out.println("La categoría existe? " + exists);
		
	}
	
	private void saveAllCategories() {
		
		categoryR.saveAll(getCategoryList());
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
		List<Category> categories =  categoryR.findAll();
		for(Category elm: categories) {
			System.out.println(elm.getId() + " " + elm.getName());
		}
	}
	
	private void deleteAllInBatch() {
		categoryR.deleteAllInBatch();
	}
	
	private void findAllCategoriesOrderByJPA() {
		List<Category> categories =  categoryR.findAll(Sort.by("name").descending());
		for(Category elm: categories) {
			System.out.println(elm.getId() + " " + elm.getName());
		}		
	}
	
	private void findAllCategoriesPagination() {
		Page<Category> page = categoryR.findAll(PageRequest.of(2, 5));
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total páginas: " + page.getTotalPages());
		for(Category elm: page) {
			System.out.println(elm.getId() + " " + elm.getName());
		}
	}
	
	
	private void findAllCategoriesPaginationOrder() {
		Page<Category> page = categoryR.findAll(PageRequest.of(2, 5, Sort.by("name")));
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total páginas: " + page.getTotalPages());
		for(Category elm: page) {
			System.out.println(elm.getId() + " " + elm.getName());
		}
	}
	
	private void findAllJobs() {
		List<Job> jobs = jobR.findAll();
		for(Job elm: jobs) {
			System.out.println(elm.getId() + " " + elm.getTitle() + " " + elm.getCategory().getName());
		}
	}
	
	private void saveJob() {
		Job job = new Job();
		job.setTitle("Profesor de matemáticas");
		job.setDescription("Escuela primaria solicita profesor para curso de matemáticas");
		job.setPostDate(new Date());
		job.setSalary(8500.0);
		job.setStatus("Aprobada");
		job.setHighlight(true);
		job.setPicture("escuela.png");
		job.setDetails("<h1>Los requisitos para profesor de matemáticas</h1>");
		
		Category category = new Category();
		category.setId(15);
		job.setCategory(category);
		
		jobR.save(job);
	}
	
	private void createProfilesApp() {
		profileR.saveAll(getProfilesApp());		
	}
	
	private List<Profile> getProfilesApp() {
		List<Profile> profiles = new LinkedList<Profile>();
		
		Profile profile1 = new Profile();
		profile1.setName("SUPERVISOR");
		
		Profile profile2 = new Profile();
		profile2.setName("ADMINISTRADOR");		
		
		Profile profile3 = new Profile();
		profile3.setName("USUARIO");
		
		profiles.add(profile1);
		profiles.add(profile2);
		profiles.add(profile3);
		
		return profiles;
	}
	
	private void createUserManyProfiles() {
		User user = new User();
		user.setName("Mario Ahumada");
		user.setEmail("ahumadamob@gmail.com");
		user.setUsername("ahumadamob");
		user.setPassword("12345");
		user.setStatus(true);
		user.setCreateDate(new Date());
		
		Profile profile1 = new Profile();
		profile1.setId(3);
		
		Profile profile2 = new Profile();
		profile2.setId(4);
		
		user.addProfile(profile1);
		user.addProfile(profile2);
		
		userR.save(user);
	}
	
	private void findUser() {
		Optional<User> optional = userR.findById(2);
		if(optional.isPresent()) {
			User user = optional.get();
			System.out.println("Usuario: " + user.getName());
			System.out.println("Perfiles:");
			for(Profile profile: user.getProfiles()) {
				System.out.println(profile.getName());
			}			
		}else {
			System.out.println("No se encontró el usuario");
		}
	}

}

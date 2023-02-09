package com.ahumadamob.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ahumadamob.model.Category;

//public interface CategoryRepository extends CrudRepository<Category, Integer> 

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

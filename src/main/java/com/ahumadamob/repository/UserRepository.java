package com.ahumadamob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}

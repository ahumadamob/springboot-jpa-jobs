package com.ahumadamob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}

package com.ahumadamob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahumadamob.model.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {

}

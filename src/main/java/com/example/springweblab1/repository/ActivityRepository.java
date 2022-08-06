package com.example.springweblab1.repository;

import com.example.springweblab1.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    // The JpaRepository interface a few JPA specific extensions that makes it more convenient.
    // For example, its findAll method returns a List instead of an Iterable which makes it easier to serialize the data.
}

package com.example.springweblab1.repository;

import com.example.springweblab1.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Notice that we are extending the JpaRepository interface (Links to an external site.) instead of CrudRepository (Links to an external site.)`.
    // The JpaRepository interface a few JPA specific extensions that makes it more convenient.
    // For example, its findAll method returns a List instead of an Iterable which makes it easier to serialize the data.
}
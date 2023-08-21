package com.example.springbootwiththymeleaf.repository;

import com.example.springbootwiththymeleaf.model.PhotoString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoString, Long> {
}

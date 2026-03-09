package com.example.restservice.repository;

import com.example.restservice.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Long> {
    List<Prof> findByNomContainingIgnoreCase(String nom);
}
package com.example.new_hospital.repository;

import com.example.new_hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String keyword, Pageable pageable);
}

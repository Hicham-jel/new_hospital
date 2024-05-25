package com.example.new_hospital;

import com.example.new_hospital.entities.Patient;
import com.example.new_hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class NewHospitalApplication implements CommandLineRunner {
@Autowired
private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(NewHospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    //sol 1 pour creer un objet
    /*Patient patient=new Patient();
    patient.setId(null);
    patient.setNom("Hicham");
    patient.setDateNaissance(new Date());
    patient.setMalade(false);
    patient.setScore(100);*/

    //2eme sol
        //Patient patient1=new Patient(null,"Amine",new Date(),false,52);
        //3eme sol
    //Patient patient2=Patient.builder().id(null).nom("Imane").dateNaissance(new Date()).malade(false).score(96).build();
       // patientRepository.save(new Patient(null,"Amine",new Date(),false,900));
        //patientRepository.save(new Patient(null,"Hicham",new Date(),false,200));
        //patientRepository.save(new Patient(null,"Imane",new Date(),true,500));
    }
}

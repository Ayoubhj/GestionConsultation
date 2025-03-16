package com.enset.gestionconsultation.init;

import com.enset.gestionconsultation.entities.Doctor;
import com.enset.gestionconsultation.entities.Patient;
import com.enset.gestionconsultation.repositories.DoctorRepository;
import com.enset.gestionconsultation.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    private DoctorRepository doctorRepository;

    private PatientRepository patientRepository;

    @Autowired
    public InitialDataLoader(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    private void loadInitialData() {
        // Check if the database is empty
        if (doctorRepository.count() == 0 && patientRepository.count() == 0) {
            // Create initial doctors
            Doctor doctor1 = new Doctor();
            doctor1.setNom("Dr. John Smith");
            doctor1.setSpeciality("Cardiology");
            doctor1.setEmail("d@gmail.com");

            Doctor doctor2 = new Doctor();
            doctor2.setNom("Dr. Jane Doe");
            doctor2.setSpeciality("Neurology");
            doctor2.setEmail("b@gmail.com");

            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);

            // Create initial patients
            Patient patient1 = new Patient();
            patient1.setNom("Alice Johnson");
            patient1.setTelephone("077E49390");
            patient1.setEmail("a@gmail.com");

            Patient patient2 = new Patient();
            patient2.setNom("Bob Williams");
            patient2.setTelephone("0506435536");
            patient1.setEmail("c@gmail.com");

            patientRepository.save(patient1);
            patientRepository.save(patient2);
        }
    }
}


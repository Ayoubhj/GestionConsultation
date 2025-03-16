package com.enset.gestionconsultation.services;

import com.enset.gestionconsultation.entities.Consultation;
import com.enset.gestionconsultation.entities.Doctor;
import com.enset.gestionconsultation.entities.Patient;
import com.enset.gestionconsultation.repositories.ConsultationRepository;
import com.enset.gestionconsultation.repositories.DoctorRepository;
import com.enset.gestionconsultation.repositories.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public ConsultationService(ConsultationRepository consultationRepository,
                               PatientRepository patientRepository,
                               DoctorRepository doctorRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void saveConsultation(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    public Consultation getConsultationById(Long id) {
        return consultationRepository.findById(id).orElse(null);
    }
}



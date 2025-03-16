package com.enset.gestionconsultation.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor {

    public Doctor() {
    }

    public Doctor(Long id, String nom, String speciality, String email, List<Consultation> consultations) {
        this.id = id;
        this.nom = nom;
        this.speciality = speciality;
        this.email = email;
        this.consultations = consultations;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String speciality;
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
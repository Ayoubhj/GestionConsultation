package com.enset.gestionconsultation.controllers;

import com.enset.gestionconsultation.entities.Consultation;
import com.enset.gestionconsultation.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Controller
public class ConsultationController {

    private final ConsultationService consultationService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text != null && !text.isEmpty()) {
                    try {
                        setValue(LocalDateTime.parse(text, dateTimeFormatter));
                    } catch (DateTimeParseException e) {
                        setValue(null);
                    }
                } else {
                    setValue(null);
                }
            }

            @Override
            public String getAsText() {
                LocalDateTime value = (LocalDateTime) getValue();
                return (value != null) ? value.format(dateTimeFormatter) : "";
            }
        });
    }

    @Autowired
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }
    @GetMapping("/consultations")
    public String listConsultations(Model model) {
        model.addAttribute("consultations", consultationService.getAllConsultations());
        return "consultations";
    }

    @GetMapping("/consultations/new")
    public String showCreateForm(Model model) {
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("patients", consultationService.getAllPatients());
        model.addAttribute("doctors", consultationService.getAllDoctors());
        return "create_consultation";
    }

    @PostMapping("/consultations")
    public String saveConsultation(@ModelAttribute("consultation") Consultation consultation) {
        consultationService.saveConsultation(consultation);
        return "redirect:/consultations";
    }

    @GetMapping("/consultations/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Consultation consultation = consultationService.getConsultationById(id);
        model.addAttribute("consultation", consultation);
        model.addAttribute("patients", consultationService.getAllPatients());
        model.addAttribute("doctors", consultationService.getAllDoctors());
        return "edit_consultation";
    }

    @PostMapping("/consultations/update")
    public String updateConsultation(@ModelAttribute("consultation") Consultation consultation) {
        consultationService.saveConsultation(consultation);
        return "redirect:/consultations";
    }

    @GetMapping("/consultations/delete/{id}")
    public String deleteConsultation(@PathVariable("id") Long id) {
        consultationService.deleteConsultation(id);
        return "redirect:/consultations";
    }
}
package com.ghostnet.controller;

import com.ghostnet.model.Person;
import com.ghostnet.dao.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Formular anzeigen
    @GetMapping("/person/form")
    public String showForm(Model model) {
        model.addAttribute("person", new Person());
        return "person-form";
    }

    // Person speichern
    @PostMapping("/person/save")
    public String savePerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person-form";
        }
        personRepository.save(person);
        return "redirect:/nets?success";
    }
}


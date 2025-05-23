package com.ghostnet.controller;

import com.ghostnet.model.Geisternetz;
import com.ghostnet.model.NetzStatus;
import com.ghostnet.model.Person;
import com.ghostnet.dao.GeisternetzRepository;
import com.ghostnet.dao.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private GeisternetzRepository geisternetzRepository;

    @Autowired
    private PersonRepository personRepository;

    // ⬇️ NEU: Weiterleitung von / auf /nets
    @GetMapping("/")
    public String redirectToNets() {
        return "redirect:/nets";
    }

    // Startseite mit allen Geisternetzen
    @GetMapping("/nets")
    public String home(Model model) {
        List<Geisternetz> netze = geisternetzRepository.findAll();
        model.addAttribute("netze", netze);
        return "nets";
    }

    // Formular für neues Netz
    @GetMapping("/nets/form")
    public String showCreateForm(Model model) {
        model.addAttribute("netz", new Geisternetz());
        model.addAttribute("personen", personRepository.findAll());
        return "form";
    }

    @PostMapping("/nets/save")
    public String saveGeisternetz(@Valid @ModelAttribute("netz") Geisternetz netz,
                                  BindingResult result,
                                  @RequestParam(value = "meldendePersonId", required = false) Long personId,
                                  @RequestParam(value = "bergendePersonId", required = false) Long bergendePersonId,
                                  @RequestParam(value = "anonym", required = false) String anonym,
                                  Model model) {

        if (result.hasErrors()) {
            model.addAttribute("personen", personRepository.findAll());
            return "form";
        }

        if (anonym == null && personId != null) {
            personRepository.findById(personId).ifPresent(netz::setMeldendePerson);
        } else {
            netz.setMeldendePerson(null);
        }

        if (bergendePersonId != null) {
            personRepository.findById(bergendePersonId).ifPresent(netz::setBergendePerson);
        }

        geisternetzRepository.save(netz);
        return "redirect:/nets";
    }


    @GetMapping("/assign")
    public String showAssignForm(Model model) {
        List<Geisternetz> offeneNetze = geisternetzRepository.findByBergendePersonIsNull();
        model.addAttribute("offeneNetze", offeneNetze);
        model.addAttribute("personen", personRepository.findAll());
        return "assign";
    }

    @PostMapping("/nets/assign")
    public String assignNet(@RequestParam Long netzId, @RequestParam Long personId) {
        Optional<Geisternetz> optionalNetz = geisternetzRepository.findById(netzId);
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalNetz.isPresent() && optionalPerson.isPresent()) {
            Geisternetz netz = optionalNetz.get();
            netz.setBergendePerson(optionalPerson.get());
            netz.setStatus(NetzStatus.BERGUNG_BEVORSTEHEND);
            geisternetzRepository.save(netz);
        }

        return "redirect:/nets";
    }

    @GetMapping("/status")
    public String showStatusForm(Model model) {
        model.addAttribute("netze", geisternetzRepository.findAll());
        model.addAttribute("statuses", NetzStatus.values());
        return "status";
    }

    @PostMapping("/nets/status")
    public String updateStatus(@RequestParam Long netzId, @RequestParam NetzStatus status) {
        Optional<Geisternetz> optionalNetz = geisternetzRepository.findById(netzId);
        if (optionalNetz.isPresent()) {
            Geisternetz netz = optionalNetz.get();
            netz.setStatus(status);
            geisternetzRepository.save(netz);
        }
        return "redirect:/nets";
    }

    @GetMapping("/lost")
    public String showLostForm(Model model) {
        model.addAttribute("netze", geisternetzRepository.findAll());
        model.addAttribute("personen", personRepository.findAll());
        return "lost";
    }

    @PostMapping("/lost")
    public String markAsLost(@RequestParam Long netzId, @RequestParam Long personId) {
        Optional<Geisternetz> optionalNetz = geisternetzRepository.findById(netzId);
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalNetz.isPresent() && optionalPerson.isPresent()) {
            Geisternetz netz = optionalNetz.get();
            if (netz.getMeldendePerson() != null) {
                netz.setStatus(NetzStatus.VERSCHOLLEN);
                geisternetzRepository.save(netz);
            }
        }

        return "redirect:/nets";
    }

    @PostMapping("/nets/done")
    public String markAsDone(@RequestParam Long id) {
        Optional<Geisternetz> optionalNetz = geisternetzRepository.findById(id);
        if (optionalNetz.isPresent()) {
            Geisternetz netz = optionalNetz.get();
            netz.setStatus(NetzStatus.GEBORGEN);
            geisternetzRepository.save(netz);
        }
        return "redirect:/nets";
    }

    @PostMapping("/done")
    public String markAsRecovered(@RequestParam Long netzId, @RequestParam Long personId) {
        Optional<Geisternetz> optionalNetz = geisternetzRepository.findById(netzId);
        Optional<Person> optionalPerson = personRepository.findById(personId);

        if (optionalNetz.isPresent() && optionalPerson.isPresent()) {
            Geisternetz netz = optionalNetz.get();
            if (netz.getBergendePerson() != null &&
                netz.getBergendePerson().getId().equals(personId) &&
                netz.getStatus() == NetzStatus.BERGUNG_BEVORSTEHEND) {
                netz.setStatus(NetzStatus.GEBORGEN);
                geisternetzRepository.save(netz);
            }
        }

        return "redirect:/nets";
    }

    @GetMapping("/done")
    public String showDonePage(Model model) {
        List<Geisternetz> offeneNetze = geisternetzRepository.findByStatus(NetzStatus.BERGUNG_BEVORSTEHEND);
        List<Person> personen = personRepository.findAll();
        model.addAttribute("offeneNetze", offeneNetze);
        model.addAttribute("personen", personen);
        return "done";
    }
}





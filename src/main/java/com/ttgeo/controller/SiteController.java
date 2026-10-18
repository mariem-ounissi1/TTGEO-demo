package com.ttgeo.controller;

import com.ttgeo.model.Site;
import com.ttgeo.repository.SiteRepository;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sites")
public class SiteController {
    private final SiteRepository sites;

    public SiteController(SiteRepository sites) { this.sites = sites; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("sites", sites.findAll());
        return "sites/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("site", new Site());
        return "sites/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("site", sites.findById(id).orElseThrow());
        return "sites/form";
    }

   @PostMapping("/save")
public String save(@Valid @ModelAttribute("site") Site site, BindingResult result) {
    if (result.hasErrors()) return "sites/form";
    try {
        sites.save(site);
    } catch (DataIntegrityViolationException e) {
        result.rejectValue("referenceSite", "duplicate", "Cette référence existe déjà");
        return "sites/form";
    }
    return "redirect:/sites";
}

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        sites.deleteById(id);
        return "redirect:/sites";
    }
}

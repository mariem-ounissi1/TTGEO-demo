package com.ttgeo.controller;

import com.ttgeo.model.AppUser;
import com.ttgeo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class ProfileController {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public ProfileController(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("user", users.findByEmail(principal.getName()).orElseThrow());
        return "profile";
    }

   // @PostMapping("/profile")
    //public String update(Principal principal, @RequestParam String nom,
                       //  @RequestParam(required = false) String newPassword) {
        //AppUser u = users.findByEmail(principal.getName()).orElseThrow();
        //if (nom != null && !nom.isBlank()) u.setNom(nom.trim());
        //if (newPassword != null && newPassword.length() >= 6) u.setPassword(encoder.encode(newPassword));
      //  users.save(u);
      //  return "redirect:/profile?saved";
    //}


    @PostMapping("/profile")
    public String update(Principal principal, @RequestParam String nom,
                     @RequestParam(required = false) String newPassword,
                     @RequestParam(required = false) String telephone,
                     @RequestParam(required = false) String cin) {
    String tel = telephone == null ? "" : telephone.trim();
    String c = cin == null ? "" : cin.trim();
    if ((!tel.isEmpty() && !tel.matches("\\d{8}")) || (!c.isEmpty() && !c.matches("\\d{8}"))) {
        return "redirect:/profile?invalid";
    }
    AppUser u = users.findByEmail(principal.getName()).orElseThrow();
    if (nom != null && !nom.isBlank()) u.setNom(nom.trim());
    if (newPassword != null && newPassword.length() >= 6) u.setPassword(encoder.encode(newPassword));
    u.setTelephone(tel.isEmpty() ? null : tel);
    u.setCin(c.isEmpty() ? null : c);
    users.save(u);
    return "redirect:/profile?saved";
    }
}

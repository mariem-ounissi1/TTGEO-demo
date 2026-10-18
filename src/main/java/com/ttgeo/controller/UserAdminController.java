package com.ttgeo.controller;

import com.ttgeo.model.AppUser;
import com.ttgeo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserAdminController {
    private final UserRepository users;
    private final PasswordEncoder encoder;


    public UserAdminController(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", users.findAll());
        return "users/list";
    }

     @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "users/new";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") AppUser user, BindingResult result,
                             @RequestParam(defaultValue = "USER") String role) {
        if (users.existsByEmail(user.getEmail())) {
            result.rejectValue("email", "dup", "Cet email est déjà utilisé");
        }
        if (result.hasErrors()) return "users/new";
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ADMIN".equals(role) ? "ADMIN" : "USER");
        users.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", users.findById(id).orElseThrow());
        return "users/form";
    }

    @PostMapping("/save/{id}")
    public String save(@PathVariable Long id, @RequestParam String nom, @RequestParam String role) {
        AppUser u = users.findById(id).orElseThrow();
        u.setNom(nom.trim());
        u.setRole("ADMIN".equals(role) ? "ADMIN" : "USER");
        users.save(u);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        users.deleteById(id);
        return "redirect:/users";
    }
}

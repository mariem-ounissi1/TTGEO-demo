package com.ttgeo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nom obligatoire")
    private String nom;
    @NotBlank(message = "Email obligatoire") @Email(message = "Email invalide")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Mot de passe obligatoire") @Size(min = 6, message = "6 caractères minimum")
    private String password;
    private String role = "USER";
    @Column(length = 8)
    private String telephone;
    @Column(length = 8)
    private String cin;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
}

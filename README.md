# TTGEO (reproduction personnelle)

Reproduction personnelle, à but de démonstration, d'une application de gestion de sites géolocalisés
sur laquelle j'ai travaillé pendant mon stage de perfectionnement à Tunisie Télécom (janv.–févr. 2023).
Ce n'est pas le code d'origine de l'entreprise : le projet a été réécrit de zéro.

## Fonctionnalités
- Inscription / authentification (Spring Security, mots de passe hachés avec BCrypt)
- Profil utilisateur (modification du nom et du mot de passe)
- Navigation avec menu selon le rôle (USER / ADMIN)
- CRUD des sites (nom, adresse, latitude, longitude)
- CRUD des utilisateurs (réservé à l'ADMIN) ; le premier compte créé est ADMIN

## Stack
Java 17, Spring Boot 3, Spring Security, Spring Data JPA, Thymeleaf, Bootstrap 5, MySQL

## Lancer le projet
1. Démarrer MySQL (la base `ttgeo` est créée automatiquement)
2. Adapter `src/main/resources/application.properties` (utilisateur/mot de passe MySQL)
3. `mvn spring-boot:run`
4. Ouvrir http://localhost:8080

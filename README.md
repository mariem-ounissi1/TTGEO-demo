# TTGeo – Gestion des sites GSM

Application web de gestion des sites GSM et de leurs données d'installation sur le terrain.

Il s'agit d'une reproduction personnelle, à but de démonstration, d'une application sur laquelle j'ai travaillé pendant mon stage de perfectionnement à Tunisie Télécom, direction régionale de Médenine (janv.–févr. 2023). Ce n'est pas le code d'origine de l'entreprise : le projet a été réécrit de zéro.

Elle permet de centraliser, au même endroit, la référence technique d'un site, son adresse IP, sa localisation (latitude et longitude) et les informations liées à son installation, au lieu de les garder dans des fichiers dispersés.

## Démonstration

Vidéo de démonstration : connexion, gestion des sites GSM et gestion des utilisateurs.

<!-- Glissez la vidéo demo.mp4 ici, directement dans l'éditeur GitHub -->

## Fonctionnalités

- Authentification par email et mot de passe, avec création de compte.
- Deux rôles : **ADMIN** et **USER**.
- Gestion des sites GSM : ajouter, consulter, modifier et supprimer.
- Pour chaque site :
  - référence du site (nom technique, unique) et adresse IP ;
  - network element et adresse complète ;
  - coordonnées GPS (latitude et longitude) ;
  - données d'installation sur le terrain : date, technicien, type d'installation, statut (en service, en maintenance, hors service) et observations.
- Validation des champs (format de l'adresse IP, plages valides de latitude et de longitude, référence déjà existante).
- Gestion des utilisateurs, réservée à l'administrateur.
- Page de profil.

## Technologies utilisées

| Partie | Technologie |
|---|---|
| Langage | Java 17 ou plus (développé avec JDK 21) |
| Framework | Spring Boot 3.2.5 (Spring MVC, Spring Data JPA, Spring Security) |
| Interface | Thymeleaf, Bootstrap 5 |
| Base de données | MySQL (via XAMPP) |
| Outils | Maven, Visual Studio Code |

## Prérequis

- JDK 17 ou plus récent
- Maven (ou le wrapper `mvnw` s'il est présent dans le projet)
- XAMPP, avec les modules **Apache** et **MySQL** démarrés
- Git

## Installation et lancement

**1. Récupérer le projet**

```bash
git clone https://github.com/mariem-ounissi1/TTGEO-demo.git
cd TTGEO-demo
```

**2. Préparer la base de données**

Démarrez MySQL depuis le panneau XAMPP. La base `ttgeo` et ses tables sont créées automatiquement par l'application au premier lancement.
Vous pouvez vérifier le résultat dans phpMyAdmin (`http://localhost/phpmyadmin`).

**3. Configurer la connexion**

Copiez le fichier d'exemple, puis adaptez-le à votre installation :

```bash
copy src\main\resources\application.properties.example src\main\resources\application.properties
```

Contenu à vérifier dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ttgeo
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Le fichier `application.properties` n'est pas publié sur GitHub afin de ne pas exposer d'identifiants.

**4. Lancer l'application**

```bash
mvn spring-boot:run
```

L'application est alors disponible sur `http://localhost:8080`.

## Comptes et rôles

- Le premier compte créé (page **Créer un compte**) reçoit automatiquement le rôle **ADMIN**.
- L'administrateur peut ensuite créer d'autres comptes depuis le menu **Utilisateurs** et leur attribuer le rôle ADMIN ou USER.
- Un utilisateur avec le rôle USER accède aux sites et à son profil. Le menu **Utilisateurs** lui est masqué.

## Structure du projet

```
TTGEO-demo/
├── src/main/java/com/ttgeo/
│   ├── controller/   # Contrôleurs web (sites, utilisateurs)
│   ├── model/        # Entités JPA (Site, AppUser)
│   ├── repository/   # Accès aux données
│   ├── security/     # Configuration de Spring Security
│   └── TtgeoApplication.java
├── src/main/resources/
│   ├── templates/    # Pages Thymeleaf (login, sites, users, profil)
│   ├── static/       # Fichiers CSS et images
│   └── application.properties.example
└── pom.xml
```

## Pistes d'amélioration

- Affichage et recherche des sites sur une carte interactive.
- Gestion des équipements réseau rattachés à chaque site.
- Export des sites au format Excel ou PDF.
- Historique des modifications d'un site.

## Auteur

Projet réalisé par **Meriem Ounissi** (ISET Gabès – Département Technologies de l'informatique).

## Remarque

Les données visibles dans la vidéo de démonstration sont des données de test. Elles ne correspondent pas à des sites réels.

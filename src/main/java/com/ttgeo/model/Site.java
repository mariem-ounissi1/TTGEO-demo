package com.ttgeo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Site {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Remplace "nom" : nom technique du site (ex: MDN_001)
    @NotBlank(message = "Référence du site obligatoire")
    @Column(unique = true)
    private String referenceSite;

    // Adresse IP du site (obligatoire, format IPv4)
    @NotBlank(message = "Adresse IP obligatoire")
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$",
             message = "Adresse IP invalide (ex: 10.12.5.20)")
    private String adresseIp;

    // Remplace "adresse"
    @NotBlank(message = "Adresse complète obligatoire")
    private String adresseComplete;

    // Network element (présent dans le diagramme de classe du rapport)
    private String networkElement;

    @NotNull(message = "Latitude obligatoire") @DecimalMin("-90") @DecimalMax("90")
    private Double latitude;
    @NotNull(message = "Longitude obligatoire") @DecimalMin("-180") @DecimalMax("180")
    private Double longitude;

    // ===== Données de l'installation sur terrain =====
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateInstallation;
    private String technicienInstallation;
    private String typeInstallation;   // ex: BTS, antenne, pylône...
    private String statut;             // ex: En service, En maintenance, Hors service
    @Column(length = 1000)
    private String observations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReferenceSite() { return referenceSite; }
    public void setReferenceSite(String referenceSite) { this.referenceSite = referenceSite; }
    public String getAdresseIp() { return adresseIp; }
    public void setAdresseIp(String adresseIp) { this.adresseIp = adresseIp; }
    public String getAdresseComplete() { return adresseComplete; }
    public void setAdresseComplete(String adresseComplete) { this.adresseComplete = adresseComplete; }
    public String getNetworkElement() { return networkElement; }
    public void setNetworkElement(String networkElement) { this.networkElement = networkElement; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public LocalDate getDateInstallation() { return dateInstallation; }
    public void setDateInstallation(LocalDate dateInstallation) { this.dateInstallation = dateInstallation; }
    public String getTechnicienInstallation() { return technicienInstallation; }
    public void setTechnicienInstallation(String technicienInstallation) { this.technicienInstallation = technicienInstallation; }
    public String getTypeInstallation() { return typeInstallation; }
    public void setTypeInstallation(String typeInstallation) { this.typeInstallation = typeInstallation; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
}
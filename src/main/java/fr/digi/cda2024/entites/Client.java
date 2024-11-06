package fr.digi.cda2024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Embedded
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "Id_banque")
    private Banque banque;

    @ManyToMany
    @JoinTable(name = "client_compte",
    joinColumns = @JoinColumn(name = "Id_client", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "Id_compte", referencedColumnName = "Id")
    )
    private Set<Compte> comptes;

    /**
     * Constructeur vide
     */
    public Client() {
        this.comptes = new HashSet<>();
    }

    /**
     * Constructeur
     * @param nom nom
     * @param prenom prénom
     * @param dateDeNaissance date de naissance
     */
    public Client(String nom, String prenom, LocalDate dateDeNaissance) {
        this(nom, prenom, dateDeNaissance, null, null, new HashSet<>());
    }

    /**
     * Constructeur
     * @param nom nom
     * @param prenom prénom
     * @param dateDeNaissance date de naissance
     * @param adresse adresse
     */
    public Client(String nom, String prenom, LocalDate dateDeNaissance, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    /**
     * Constructeur
     * @param nom nom
     * @param prenom prénom
     * @param dateDeNaissance date de naissance
     * @param adresse adresse
     * @param banque banque rattachée
     * @param comptes comptes rattachés
     */
    public Client(String nom, String prenom, LocalDate dateDeNaissance, Adresse adresse, Banque banque, Set<Compte> comptes) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.banque = banque;
        this.comptes = comptes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("Id=").append(Id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", dateDeNaissance=").append(dateDeNaissance);
        sb.append(", adresse: ").append(adresse);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Id == client.Id && Objects.equals(nom, client.nom) && Objects.equals(prenom, client.prenom) && Objects.equals(dateDeNaissance, client.dateDeNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, prenom, dateDeNaissance);
    }

    /**
     * Ajouter un nouveau compte appartenant au client.
     * @param compte compte
     */
    public void addCompte(Compte compte) {
        this.comptes.add(compte);
    }

    /** Getter
     * @return Id
     */
    public int getId() {
        return Id;
    }

    /** Getter
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /** Setter
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /** Getter
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /** Setter
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /** Getter
     * @return dateDeNaissance
     */
    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    /** Setter
     * @param dateDeNaissance dateDeNaissance
     */
    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    /** Getter
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /** Setter
     * @param adresse adresse
     */
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    /** Getter
     * @return banque
     */
    public Banque getBanque() {
        return banque;
    }

    /** Setter
     * @param banque banque
     */
    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    /** Getter
     * @return comptes
     */
    public Set<Compte> getComptes() {
        return comptes;
    }

    /** Setter
     * @param comptes comptes
     */
    public void setComptes(Set<Compte> comptes) {
        this.comptes = comptes;
    }
}

package fr.digi.cda2024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Banque")
public class Banque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "nom")
    private String nom;

    // cascade : modifications apportées à Banque s'applique à Client
    @OneToMany(mappedBy = "banque", cascade = CascadeType.ALL)
    private Set<Client> clients;

    // bloc d'initilisation : évite de répéter ce code dans plusieurs constructeurs
    {
        this.clients = new HashSet<>();
    }

    /**
     * Constructeur vide
     */
    public Banque() {
    }

    /**
     * Constructeur
     * @param nom nom
     */
    public Banque(String nom) {
        this(nom, new HashSet<>());
    }

    /**
     * Constructeur
     * @param nom nom
     * @param clients set de clients
     */
    public Banque(String nom, Set<Client> clients) {
        this.nom = nom;
        this.clients = clients;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Banque{");
        sb.append("Id=").append(Id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Banque banque)) return false;
        return Id == banque.Id && Objects.equals(nom, banque.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom);
    }

    /**
     * Ajoute un client à la liste des clients de la banque,
     * et ajoute une banque rattachée au client (bidirectionnel).
     * @param client nouveau client de la banque
     */
    public void addClient(Client client) {
        client.setBanque(this);
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
     * @return clients
     */
    public Set<Client> getClients() {
        return clients;
    }

    /** Setter
     * @param clients clients
     */
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}

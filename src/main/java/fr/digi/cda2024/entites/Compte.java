package fr.digi.cda2024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Compte")
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int Id;

    @Column(name = "numero", length = 10, nullable = false)
    protected String numero;

    @Column(name = "solde", nullable = false)
    protected double solde;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    protected Set<Operation> operations;

    @ManyToMany(mappedBy = "comptes")
    protected Set<Client> clients;

    {
        this.operations = new HashSet<>();
        this.clients = new HashSet<>();
    }

    /**
     * Constructeur vide
     */
    public Compte() {
    }

    /**
     * Constructeur
     * @param numero numéro de compte
     * @param solde solde
     */
    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
        this.operations = new HashSet<>();
        this.clients = new HashSet<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("Id=").append(Id);
        sb.append(", numero='").append(numero).append('\'');
        sb.append(", solde=").append(solde);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compte compte)) return false;
        return Id == compte.Id && Double.compare(solde, compte.solde) == 0 && Objects.equals(numero, compte.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, numero, solde);
    }

    /**
     * Ajoute une nouvelle opération au set des opérations du client,
     * et ajoute l'opération rattachée au compte (bidirectionnel).
     * @param operation opération
     */
    public void addOperation(Operation operation) {
        operation.setCompte(this);
    }

    /**
     * Ajoute un nouveau client gestionnaire du compte.
     * @param client client
     */
    public void addClient(Client client) {
        this.clients.add(client);
    }

    /** Getter
     * @return Id
     */
    public int getId() {
        return Id;
    }

    /** Getter
     * @return numero
     */
    public String getNumero() {
        return numero;
    }

    /** Setter
     * @param numero numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /** Getter
     * @return solde
     */
    public double getSolde() {
        return solde;
    }

    /** Setter
     * @param solde solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

    /** Getter
     * @return operations
     */
    public Set<Operation> getOperations() {
        return operations;
    }

    /** Setter
     * @param operations operations
     */
    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
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

package fr.digi.cda2024.entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Operation")
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int Id;
    
    @Column(name="montant", nullable = false)
    protected double montant;

    @Column(name="motif")
    protected String motif;

    @ManyToOne
    @JoinColumn(name = "Id_compte")
    protected Compte compte;

    /**
     * Constructeur vide
     */
    public Operation() {
    }

    /**
     * Constructeur
     * @param montant montant
     * @param motif motif
     * @param compte compte rattaché
     */
    public Operation(double montant, String motif, Compte compte) {
        this.montant = montant;
        this.motif = motif;
        this.compte = compte;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Operation{");
        sb.append("Id=").append(Id);
        sb.append(", montant=").append(montant);
        sb.append(", motif='").append(motif).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation operation)) return false;
        return Id == operation.Id && Double.compare(montant, operation.montant) == 0 && Objects.equals(motif, operation.motif) && Objects.equals(compte, operation.compte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, montant, motif, compte);
    }

    /** Getter
     * @return Id
     */
    public int getId() {
        return Id;
    }

    /** Getter
     * @return montant
     */
    public double getMontant() {
        return montant;
    }

    /** Setter
     * @param montant montant
     */
    public void setMontant(double montant) {
        this.montant = montant;
    }

    /** Getter
     * @return motif
     */
    public String getMotif() {
        return motif;
    }

    /** Setter
     * @param motif motif
     */
    public void setMotif(String motif) {
        this.motif = motif;
    }

    /** Getter
     * @return compte
     */
    public Compte getCompte() {
        return compte;
    }

    /** Setter (bidirectionnel)
     * @param compte compte
     */
    public void setCompte(Compte compte) {
        if (this.compte != null) {
            this.compte.getOperations().remove(this);
        }

        this.compte = compte;

        if (this.compte != null) {
            this.compte.getOperations().add(this);
        }
    }
}

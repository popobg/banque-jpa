package fr.digi.cda2024.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Livret_A")
public class LivretA extends Compte {
    @Column(name = "taux", nullable = false)
    private double taux;

    /**
     * Constructeur vide
     */
    public LivretA() {
    }

    /**
     * Constructeur
     * @param numero numéro de compte
     * @param solde solde
     * @param taux taux
     */
    public LivretA(String numero, double solde, double taux) {
        super(numero, solde);
        this.taux = taux;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("Id=").append(Id);
        sb.append(", type = livret A");
        sb.append(", numero='").append(numero).append('\'');
        sb.append(", solde=").append(solde);
        sb.append(", taux=").append(taux);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivretA livretA)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(taux, livretA.taux) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taux);
    }

    /** Getter
     * @return taux
     */
    public double getTaux() {
        return taux;
    }

    /** Setter
     * @param taux taux
     */
    public void setTaux(double taux) {
        this.taux = taux;
    }
}

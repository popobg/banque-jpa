package fr.digi.cda2024.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "Virement")
public class Virement extends Operation {
    @Column(name = "beneficiaire")
    private String beneficiaire;

    /**
     * Constructeur vide
     */
    public Virement() {
    }

    /**
     * Constructeur
     * @param montant montant
     * @param motif motif
     * @param beneficiaire bénéficiaire
     */
    public Virement(double montant, String motif, Compte compte, String beneficiaire) {
        super(montant, motif, compte);
        this.beneficiaire = beneficiaire;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Virement{");
        sb.append("beneficiaire='").append(beneficiaire).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Virement virement)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(beneficiaire, virement.beneficiaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), beneficiaire);
    }

    /** Getter
     * @return beneficiaire
     */
    public String getBeneficiaire() {
        return beneficiaire;
    }

    /** Setter
     * @param beneficiaire beneficiaire
     */
    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }
}

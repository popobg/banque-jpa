package fr.digi.cda2024.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "Assurance_vie")
public class AssuranceVie extends Compte{
    @Column(name = "taux", nullable = false)
    private double taux;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    /**
     * Constructeur vide
     */
    public AssuranceVie() {
    }

    /**
     * Constructeur
     * @param numero numéro de compte
     * @param solde solde
     * @param taux taux
     * @param dateFin date de fin de contrat
     */
    public AssuranceVie(String numero, double solde, double taux, LocalDate dateFin) {
        super(numero, solde);
        this.taux = taux;
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        DateTimeFormatter formateurDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        final StringBuilder sb = new StringBuilder("Compte{");
        sb.append("Id=").append(Id);
        sb.append(", type = assurance vie");
        sb.append(", numero='").append(numero).append('\'');
        sb.append(", solde=").append(solde);
        sb.append(", taux=").append(taux);

        if (dateFin != null) {
            sb.append(", date de fin=").append(dateFin.format(formateurDate));
        }
        else {
            sb.append(", date de fin=no data");
        }

        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssuranceVie that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(taux, that.taux) == 0 && Objects.equals(dateFin, that.dateFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taux, dateFin);
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

    /** Getter
     * @return dateFin
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /** Setter
     * @param dateFin dateFin
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
}

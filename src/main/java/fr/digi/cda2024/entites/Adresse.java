package fr.digi.cda2024.entites;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Adresse {
    private int numero;
    private String rue;
    private int codePostal;
    private String ville;

    /**
     * Constructeur vide
     */
    public Adresse() {
    }

    /**
     * Constructeur
     * @param numero numéro de rue
     * @param rue libellé de la rue
     * @param codePostal code postal
     * @param ville nom de la ville
     */
    public Adresse(int numero, String rue, int codePostal, String ville) {
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("numero=").append(numero);
        sb.append(", rue='").append(rue).append('\'');
        sb.append(", code postal='").append(codePostal).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adresse adresse)) return false;
        return numero == adresse.numero && codePostal == adresse.codePostal && Objects.equals(rue, adresse.rue) && Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, rue, codePostal, ville);
    }

    /** Getter
     * @return numero
     */
    public int getNumero() {
        return numero;
    }

    /** Setter
     * @param numero numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /** Getter
     * @return rue
     */
    public String getRue() {
        return rue;
    }

    /** Setter
     * @param rue rue
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /** Getter
     * @return codePostal
     */
    public int getCodePostal() {
        return codePostal;
    }

    /** Setter
     * @param codePostal codePostal
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    /** Getter
     * @return ville
     */
    public String getVille() {
        return ville;
    }

    /** Setter
     * @param ville ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }
}

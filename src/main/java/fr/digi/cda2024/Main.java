package fr.digi.cda2024;

import fr.digi.cda2024.entites.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Création des instances d'objets
        Adresse adresse1 = new Adresse(3, "rue du Môme", 31300, "Toulouse");

        Banque banquePop = new Banque("Banque Pop");

        Compte compte = new Compte("1248805A", 2_000);
        LivretA livretA = new LivretA("12300A47", 12_000, 1.2);
        AssuranceVie assVie = new AssuranceVie("350000BB", 30_000, 3, LocalDate.of(2030, 9, 1));

        Set<Compte> comptesEmmanuel = new HashSet<>();
        Collections.addAll(comptesEmmanuel, livretA);
        Set<Compte> comptesElisabeth = new HashSet<>();
        comptesElisabeth.add(assVie);

        Operation operation = new Operation(124.5, "Assurance", assVie);
        Virement virement = new Virement(18.9, "Remboursement", compte, "Marie");
        compte.addOperation(virement);
        assVie.addOperation(operation);

        Client client1 = new Client("Georges", "Emmanuel", LocalDate.of(1963, 12, 18), adresse1, banquePop, comptesEmmanuel);
        Client client2 = new Client("Georges", "Elisabeth", LocalDate.of(1966, 02, 20), adresse1, banquePop, comptesElisabeth);

        compte.addClient(client1);
        livretA.addClient(client1);
        assVie.addClient(client2);

        // Persistance des données (communication JPA - DB)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(banquePop);
        em.persist(compte);
        em.persist(livretA);
        em.persist(assVie);
        em.persist(operation);
        em.persist(virement);
        em.persist(client1);
        em.persist(client2);

        Client clientRecherche = em.find(Client.class, 1);

        if (clientRecherche != null) {
            System.out.println(clientRecherche);
        }

        LivretA livretARecherche = (LivretA) em.find(Compte.class, 2);

        if (livretARecherche != null) {
            System.out.println(livretARecherche);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
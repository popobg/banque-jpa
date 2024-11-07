package fr.digi.cda2024;

import fr.digi.cda2024.entites.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Création des instances d'objets
        Adresse adresse1 = new Adresse(3, "rue du Môme", 31300, "Toulouse");

        Banque banquePop = new Banque("Banque Pop");
        Banque BNP = new Banque("BNP");

        Compte compte = new Compte("1248805A", 2_000);
        LivretA livretA = new LivretA("12300A47", 12_000, 1.2);
        AssuranceVie assVie = new AssuranceVie("350000BB", 30_000, 3, LocalDate.of(2030, 9, 1));

        Set<Compte> comptesEmmanuel = new HashSet<>();
        Collections.addAll(comptesEmmanuel, livretA, compte);
        Set<Compte> comptesElisabeth = new HashSet<>();
        Collections.addAll(comptesElisabeth, assVie, compte);

        Operation operation1 = new Operation(124.5, "Assurance", assVie);
        Operation operation2 = new Operation(5, "Cotisation", assVie);
        Virement virement1 = new Virement(18.9, "Remboursement", compte, "Marie");
        Virement virement2 = new Virement(69.9, "Chaussures", compte, "La Halle");
        compte.addOperation(virement1);
        compte.addOperation(operation2);
        assVie.addOperation(operation1);
        livretA.addOperation(virement2);

        Client client1 = new Client("Georges", "Emmanuel", LocalDate.of(1963, 12, 18), adresse1, banquePop, comptesEmmanuel);
        Client client2 = new Client("Georges", "Elisabeth", LocalDate.of(1966, 02, 20), adresse1, banquePop, comptesElisabeth);
        client2.setBanque(BNP);

        compte.addClient(client1);
        compte.addClient(client2);
        livretA.addClient(client1);
        assVie.addClient(client2);

        // Persistance des données (communication JPA - DB)
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // config cascade dans banque : permet de persister les clients directement
        // config cascade dans client : permet de persister les comptes
        // config cascade dans compte : permet de persister les opérations
        em.persist(banquePop);
        em.persist(BNP);

        Client clientRecherche = em.find(Client.class, 1);

        if (clientRecherche != null) {
            System.out.println("Client d'Id = 1 :");
            System.out.println(clientRecherche);
            System.out.println();
        }

        TypedQuery<Compte> queryComptes = em.createQuery("from Compte c where c.solde > 3000", Compte.class);
        List<Compte> comptes = queryComptes.getResultList();

        if (comptes != null) {
            System.out.println("Comptes dont le solde est supérieur à 3000€ :");
            for(Compte c: comptes) {
                System.out.println(c);
            }
            System.out.println();
        }

        TypedQuery<Compte> queryComptesOpe = em.createQuery("select c from Compte c JOIN c.operations o WHERE o.montant > 20", Compte.class);
        List<Compte> comptesOpe = queryComptesOpe.getResultList();

        if (comptesOpe != null) {
            System.out.println("Les comptes dont au moins une opération est supérieure à 20€ sont :");
            for (Compte c: comptesOpe) {
                System.out.println(c);
            }
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
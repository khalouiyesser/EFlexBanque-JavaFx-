package services;

import Entities.DemandeStage;
import Entities.OffreDeStage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RechercheStream {
    ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();

    //Recherche les demandes par le numéro de téléphone
    public List<DemandeStage> rechercheParNumeroTelephone(int a) throws SQLException {

        List<DemandeStage>list = serviceDemandeStage.afficher();
        List<DemandeStage> filteredList = list.stream()
                .filter(demandeStage -> demandeStage.getNumeroTelephone() == a)
                .collect(Collectors.toList());
        System.out.println("stram" + a);
        return filteredList;
    }

    // trier les demande par le numéro de téléphone.
    public List<DemandeStage> trierParScore(List<DemandeStage> liste) {
        return liste.stream()
                .sorted(Comparator.comparingInt(DemandeStage::getScore).reversed())
                .collect(Collectors.toList());
    }

    // recherche par le domaine.
    public List<OffreDeStage> rechercheParDomaine(String domaine){
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
        List<OffreDeStage> list = new ArrayList<>();
        return list.stream()
                .filter(offre -> offre.getDomaine().equals(domaine))
                .collect(Collectors.toList());
    }
    // recherche par demande Par offre.
    public List<DemandeStage> rechercheDemandeParOffre(int idOffre) throws SQLException {
        List<DemandeStage> list = serviceDemandeStage.afficher();
        return list.stream()
                .filter(demande -> demande.getId_offre() == idOffre)
                .collect(Collectors.toList());
    }

    // count the number of "demande" by "offre"
    public long nombreDemandesParOffre(int idOffreRecherche) throws SQLException {
        List<DemandeStage> demandes = serviceDemandeStage.afficher();
        long nombreDemandes = demandes.stream()
                .filter(demande -> demande.getId_offre() == idOffreRecherche)
                .count();
        return nombreDemandes;
    }



}
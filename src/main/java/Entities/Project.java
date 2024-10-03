package Entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Project extends AbstractEntity {
    private int id;

    private String nomProjet;
    private String img;
    private String categorie;
    private String descriptionProjet;
    private double budgetProjet;
    private LocalDateTime dateCreation;
    private int dureeProjet;
    private int statutProjet;
    private static String searchValue;
    private Set<Evenement> evenements = new HashSet<>();


    public Project(int id, String nomProjet, String img, String categorie, String descriptionProjet, double budgetProjet, LocalDateTime dateCreation, int dureeProjet, int statutProjet) {
        super(id);

        this.nomProjet = nomProjet;
        this.img = img;
        this.categorie = categorie;
        this.descriptionProjet = descriptionProjet;
        this.budgetProjet = budgetProjet;
        this.dateCreation = dateCreation;
        this.dureeProjet = dureeProjet;
        this.statutProjet = statutProjet;
    }
    public static String getSearchValue() {
        return searchValue;
    }

    public Project(int id) {
        this.id = id;
    }

    public Project() {

    }

    public Set<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Set<Evenement> evenements) {
        this.evenements = evenements;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id +
                ", nomProjet='" + nomProjet + '\'' +
                ", img='" + img + '\'' +
                ", categorie='" + categorie + '\'' +
                ", descriptionProjet='" + descriptionProjet + '\'' +
                ", budgetProjet=" + budgetProjet +
                ", dateCreation=" + dateCreation +
                ", dureeProjet=" + dureeProjet +
                ", statutProjet=" + statutProjet +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }


    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public double getBudgetProjet() {
        return budgetProjet;
    }

    public void setBudgetProjet(double budgetProjet) {
        this.budgetProjet = budgetProjet;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getDureeProjet() {
        return dureeProjet;
    }

    public void setDureeProjet(int dureeProjet) {
        this.dureeProjet = dureeProjet;
    }

    public int getStatutProjet() {
        return statutProjet;
    }

    public void setStatutProjet(int statutProjet) {
        this.statutProjet = statutProjet;
    }
}

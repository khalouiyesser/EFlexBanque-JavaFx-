package Entities;

import java.time.LocalDateTime;
import java.util.List;

public class Evenement extends AbstractEntity {
    private int id;
    public static int actionTest = 0;

    private int remise;
    public static int getIdEvenement() {
        return idEvenement;
    }

    public static void setIdEvenement(int idEvenement) {
        Evenement.idEvenement = idEvenement;
    }

    private static int idEvenement;

    private static String searchValue;

    private String nom;
    private String img;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String lieu;
    private String organisateur;
    private double prix;
    private int likes;
    private int dislikes;
    private Integer projectId;

    private List<Commentaire> commentaires;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Evenement(int id, String nom, String description, LocalDateTime dateDebut, LocalDateTime dateFin, String lieu, String organisateur, double prix, int likes, int dislikes, Integer projectId) {
        super(id);

        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.organisateur = organisateur;
        this.prix = prix;
        this.likes = likes;
        this.dislikes = dislikes;
        this.projectId = projectId;
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement() {

    }

    public static String getSearchValue() {
        return searchValue;
    }

    public static void setSearchValue(String searchValue) {
        Evenement.searchValue = searchValue;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public int getRemise() {
        return remise;
    }
    public void setRemise(int remise) {
        this.remise = remise;
    }
    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", lieu='" + lieu + '\'' +
                ", organisateur='" + organisateur + '\'' +
                ", prix=" + prix +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", projectId=" + projectId +
                ", commentaires=" + commentaires +
                '}';
    }



    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(String organisateur) {
        this.organisateur = organisateur;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}

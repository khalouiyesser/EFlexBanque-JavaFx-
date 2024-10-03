package Entities.actualites;

import java.time.LocalDateTime;

public class Article {
    private Integer id;
    private String nom_aut_art;
    private String adr_aut_art;
    private LocalDateTime date_pub_art;
    private Integer duree_art;
    private String categorie_art;
    private String titre_art;
    private String contenu_art;
    private String piecejointe_art;
    private Integer user_id;
    private String image_art;
    private int likes=0;
    private int dislikes=0;
    private int totalReactions;
    private int nbComments;
    private int nbShares;
    private String qrCode;
    public Article() {
    }

    public Article(Integer id, String nom_aut_art, String adr_aut_art, LocalDateTime date_pub_art, Integer duree_art, String categorie_art, String titre_art, String contenu_art, String piecejointe_art, Integer user_id, String image_art, int likes, int dislikes) {
        this.id = id;
        this.nom_aut_art = nom_aut_art;
        this.adr_aut_art = adr_aut_art;
        this.date_pub_art = date_pub_art;
        this.duree_art = duree_art;
        this.categorie_art = categorie_art;
        this.titre_art = titre_art;
        this.contenu_art = contenu_art;
        this.piecejointe_art = piecejointe_art;
        this.user_id = user_id;
        this.image_art = image_art;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Article(LocalDateTime date_pub_art, Integer duree_art, String categorie_art, String titre_art, String contenu_art) {
        this.date_pub_art = date_pub_art;
        this.duree_art = duree_art;
        this.categorie_art = categorie_art;
        this.titre_art = titre_art;
        this.contenu_art = contenu_art;
    }

    public Article(Integer id, String nom_aut_art, String adr_aut_art, LocalDateTime date_pub_art, Integer duree_art, String categorie_art, String titre_art, String contenu_art, String piecejointe_art, String image_art, int likes, int dislikes) {
        this.id = id;
        this.nom_aut_art = nom_aut_art;
        this.adr_aut_art = adr_aut_art;
        this.date_pub_art = date_pub_art;
        this.duree_art = duree_art;
        this.categorie_art = categorie_art;
        this.titre_art = titre_art;
        this.contenu_art = contenu_art;
        this.piecejointe_art = piecejointe_art;
        this.image_art = image_art;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Article(String nom_aut_art, String adr_aut_art, LocalDateTime date_pub_art, Integer duree_art, String categorie_art, String titre_art, String contenu_art, String piecejointe_art, String image_art , String qrCode) {
        this.nom_aut_art = nom_aut_art;
        this.adr_aut_art = adr_aut_art;
        this.date_pub_art = date_pub_art;
        this.duree_art = duree_art;
        this.categorie_art = categorie_art;
        this.titre_art = titre_art;
        this.contenu_art = contenu_art;
        this.piecejointe_art = piecejointe_art;
        this.image_art = image_art;
        this.qrCode=qrCode;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_aut_art() {
        return nom_aut_art;
    }

    public void setNom_aut_art(String nom_aut_art) {
        this.nom_aut_art = nom_aut_art;
    }

    public String getAdr_aut_art() {
        return adr_aut_art;
    }

    public void setAdr_aut_art(String adr_aut_art) {
        this.adr_aut_art = adr_aut_art;
    }

    public LocalDateTime getDate_pub_art() {
        return date_pub_art;
    }

    public void setDate_pub_art(LocalDateTime date_pub_art) {
        this.date_pub_art = date_pub_art;
    }

    public Integer getDuree_art() {
        return duree_art;
    }

    public void setDuree_art(Integer duree_art) {
        this.duree_art = duree_art;
    }

    public String getCategorie_art() {
        return categorie_art;
    }

    public void setCategorie_art(String categorie_art) {
        this.categorie_art = categorie_art;
    }

    public String getTitre_art() {
        return titre_art;
    }

    public void setTitre_art(String titre_art) {
        this.titre_art = titre_art;
    }

    public String getContenu_art() {
        return contenu_art;
    }

    public void setContenu_art(String contenu_art) {
        this.contenu_art = contenu_art;
    }

    public String getPiecejointe_art() {
        return piecejointe_art;
    }

    public void setPiecejointe_art(String piecejointe_art) {
        this.piecejointe_art = piecejointe_art;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getImage_art() {
        return image_art;
    }

    public void setImage_art(String image_art) {
        this.image_art = image_art;
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
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom_aut_art='" + nom_aut_art + '\'' +
                ", adr_aut_art='" + adr_aut_art + '\'' +
                ", date_pub_art=" + date_pub_art +
                ", duree_art=" + duree_art +
                ", categorie_art='" + categorie_art + '\'' +
                ", titre_art='" + titre_art + '\'' +
                ", contenu_art='" + contenu_art + '\'' +
                ", piecejointe_art='" + piecejointe_art + '\'' +
                ", image_art='" + image_art + '\'' +
                '}';
    }


    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbShares() {
        return nbShares;
    }

    public void setNbShares(int nbShares) {
        this.nbShares = nbShares;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

}

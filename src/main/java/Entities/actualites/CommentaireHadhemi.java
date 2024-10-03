package Entities.actualites;

import java.time.LocalDateTime;

public class CommentaireHadhemi {

    private Integer id,like;
    private String contenu;
    private LocalDateTime date_creation;
    private String nom_aut_com;
    private Integer article_id;
    private Integer user_id;
    private String image_u;
    private Article article;

    public CommentaireHadhemi() {
    }

    public CommentaireHadhemi(Integer id, String contenu, LocalDateTime date_creation, String nom_aut_com, Integer article_id, Integer user_id, String image_u,int like) {
        this.id = id;
        this.contenu = contenu;
        this.date_creation = date_creation;
        this.nom_aut_com = nom_aut_com;
        this.article_id = article_id;
        this.user_id = user_id;
        this.image_u = image_u;
        this.like = like;
    }

    public CommentaireHadhemi(Integer id, String contenu, LocalDateTime date_creation, String nom_aut_com, Integer article_id, String image_u,int like) {
        this.id = id;
        this.contenu = contenu;
        this.date_creation = date_creation;
        this.nom_aut_com = nom_aut_com;
        this.article_id = article_id;
        this.image_u = image_u;
        this.like = like;
    }

    public CommentaireHadhemi(Integer id, String contenu, LocalDateTime date_creation, String nom_aut_com, String image_u,int like) {
        this.id = id;
        this.contenu = contenu;
        this.date_creation = date_creation;
        this.nom_aut_com = nom_aut_com;
        this.image_u = image_u;
        this.like = like;
    }

    public CommentaireHadhemi(String contenu, LocalDateTime date_creation, String nom_aut_com, Integer article_id, String image_u,int like) {
        this.contenu = contenu;
        this.date_creation = date_creation;
        this.nom_aut_com = nom_aut_com;
        this.article_id = article_id;
        this.image_u = image_u;
        this.like = like;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDateTime date_creation) {
        this.date_creation = date_creation;
    }

    public String getNom_aut_com() {
        return nom_aut_com;
    }

    public void setNom_aut_com(String nom_aut_com) {
        this.nom_aut_com = nom_aut_com;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getImage_u() {
        return image_u;
    }

    public void setImage_u(String image_u) {
        this.image_u = image_u;
    }

    @Override
    public String toString() {
        return "CommentaireHadhemi{" +
                "id=" + id +
                ", contenu='" + contenu + '\'' +
                ", date_creation=" + date_creation +
                ", nom_aut_com='" + nom_aut_com + '\'' +
                ", article_id=" + article_id +
                ", user_id=" + user_id +
                ", image_u='" + image_u + '\'' +
                '}';
    }

    public void setArticle(Article article) {
        this.article = article;

    }
    public Article getArticle() {
        return this.article;
    }
}

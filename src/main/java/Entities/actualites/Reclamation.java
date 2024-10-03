package Entities.actualites;

import java.time.LocalDateTime;

public class Reclamation {
    private Integer id;
    private String objet_rec;
    private String contenu_rec;
    private String adr_rec;
    private String nom_aut_rec;
    private String dep_rec;
    private String statut_rec;
    private String piece_jrec;
    private LocalDateTime date_rec;
    private Integer user_id;

    public Reclamation() {
    }

    public Reclamation(Integer id, String objet_rec, String contenu_rec, String adr_rec, String nom_aut_rec, String dep_rec, String statut_rec, String piece_jrec, LocalDateTime date_rec, Integer user_id) {
        this.id = id;
        this.objet_rec = objet_rec;
        this.contenu_rec = contenu_rec;
        this.adr_rec = adr_rec;
        this.nom_aut_rec = nom_aut_rec;
        this.dep_rec = dep_rec;
        this.statut_rec = statut_rec;
        this.piece_jrec = piece_jrec;
        this.date_rec = date_rec;
        this.user_id = user_id;
    }

    public Reclamation(String objet_rec, String contenu_rec, String adr_rec, String nom_aut_rec, String dep_rec, String statut_rec, String piece_jrec, LocalDateTime date_rec,int user_id) {
        this.objet_rec = objet_rec;
        this.contenu_rec = contenu_rec;
        this.adr_rec = adr_rec;
        this.nom_aut_rec = nom_aut_rec;
        this.dep_rec = dep_rec;
        this.statut_rec = statut_rec;
        this.piece_jrec = piece_jrec;
        this.date_rec = date_rec;
        this.user_id = user_id;
    }

    public Reclamation(String text, String text1, String adresse, String nom, String selectedDepartment, String pieceJointe, String statut, LocalDateTime dateTime, String image) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjet_rec() {
        return objet_rec;
    }

    public void setObjet_rec(String objet_rec) {
        this.objet_rec = objet_rec;
    }

    public String getContenu_rec() {
        return contenu_rec;
    }

    public void setContenu_rec(String contenu_rec) {
        this.contenu_rec = contenu_rec;
    }

    public String getAdr_rec() {
        return adr_rec;
    }

    public void setAdr_rec(String adr_rec) {
        this.adr_rec = adr_rec;
    }

    public String getNom_aut_rec() {
        return nom_aut_rec;
    }

    public void setNom_aut_rec(String nom_aut_rec) {
        this.nom_aut_rec = nom_aut_rec;
    }

    public String getDep_rec() {
        return dep_rec;
    }

    public void setDep_rec(String dep_rec) {
        this.dep_rec = dep_rec;
    }

    public String getStatut_rec() {
        return statut_rec;
    }

    public void setStatut_rec(String statut_rec) {
        this.statut_rec = statut_rec;
    }

    public String getPiece_jrec() {
        return piece_jrec;
    }

    public void setPiece_jrec(String piece_jrec) {
        this.piece_jrec = piece_jrec;
    }

    public LocalDateTime getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(LocalDateTime date_rec) {
        this.date_rec = date_rec;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", objet_rec='" + objet_rec + '\'' +
                ", contenu_rec='" + contenu_rec + '\'' +
                ", adr_rec='" + adr_rec + '\'' +
                ", nom_aut_rec='" + nom_aut_rec + '\'' +
                ", dep_rec='" + dep_rec + '\'' +
                ", statut_rec='" + statut_rec + '\'' +
                ", piece_jrec='" + piece_jrec + '\'' +
                ", date_rec=" + date_rec +
                ", user_id=" + user_id +
                '}';
    }



}

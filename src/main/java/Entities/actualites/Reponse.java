package Entities.actualites;

import java.time.LocalDateTime;

public class Reponse {

    private Integer id;
    private Integer reclamation_id;
    private Integer user_id;
    private String adr_rep;
    private LocalDateTime date_rep;
    private String contenu_rep;
    private String piece_jrep;
    private Reclamation reclamation;

    public Reponse() {
    }

    public Reponse(Integer id, Integer reclamation_id, Integer user_id, String adr_rep, LocalDateTime date_rep, String contenu_rep, String piece_jrep) {
        this.id = id;
        this.reclamation_id = reclamation_id;
        this.user_id = user_id;
        this.adr_rep = adr_rep;
        this.date_rep = date_rep;
        this.contenu_rep = contenu_rep;
        this.piece_jrep = piece_jrep;
    }

    public Reponse(Integer reclamation_id, String adr_rep, LocalDateTime date_rep, String contenu_rep, String piece_jrep) {
        this.reclamation_id = reclamation_id;
        this.adr_rep = adr_rep;
        this.date_rep = date_rep;
        this.contenu_rep = contenu_rep;
        this.piece_jrep = piece_jrep;
    }

    public Reponse(String adr_rep, LocalDateTime date_rep, String contenu_rep, String piece_jrep) {
        this.adr_rep = adr_rep;
        this.date_rep = date_rep;
        this.contenu_rep = contenu_rep;
        this.piece_jrep = piece_jrep;
        this.reclamation = reclamation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(Integer reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getAdr_rep() {
        return adr_rep;
    }

    public void setAdr_rep(String adr_rep) {
        this.adr_rep = adr_rep;
    }

    public LocalDateTime getDate_rep() {
        return date_rep;
    }

    public void setDate_rep(LocalDateTime date_rep) {
        this.date_rep = date_rep;
    }

    public String getContenu_rep() {
        return contenu_rep;
    }

    public void setContenu_rep(String contenu_rep) {
        this.contenu_rep = contenu_rep;
    }

    public String getPiece_jrep() {
        return piece_jrep;
    }

    public void setPiece_jrep(String piece_jrep) {
        this.piece_jrep = piece_jrep;
    }
    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;

    }

    public Reclamation getReclamation() {
        return this.reclamation;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "id=" + id +
                ", reclamation_id=" + reclamation_id +
                ", user_id=" + user_id +
                ", adr_rep='" + adr_rep + '\'' +
                ", date_rep=" + date_rep +
                ", contenu_rep='" + contenu_rep + '\'' +
                ", piece_jrep='" + piece_jrep + '\'' +
                ", reclamation=" + reclamation +
                '}';
    }
}

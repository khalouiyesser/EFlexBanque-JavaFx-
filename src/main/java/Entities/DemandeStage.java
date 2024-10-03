package Entities;

import java.sql.Date;
import java.util.Objects;

public class DemandeStage {

    String nom,prenom,email,lettremotivation,cv,domaine,etat;
    int id,numeroTelephone,score,id_offre;
    Date date;
    public DemandeStage(int id, int id_offre, String nom, String prenom, String email, int numeroTelephone,String lettremotivation,String cv, String domaine,String etat, Date date, int score){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.id_offre = id_offre;
        this.score = score;
        this.date = date;

    }
    public DemandeStage( int id_offre, String nom, String prenom, String email, int numeroTelephone,String lettremotivation,String cv, String domaine,String etat, Date date, int score){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;

    }
    public DemandeStage( String nom, String prenom, String email, int numeroTelephone,String lettremotivation,String cv, String domaine,String etat, Date date){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;

    }
    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, String etat, int numeroTelephone, int score, int id_offre, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, String etat, int id, int numeroTelephone, int score, int id_offre, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.id = id;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;
    }

    public DemandeStage(int id, String nom, String prenom, String email, String lettremotivation, String cv, String domaine, int numeroTelephone, int score, Date date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        //this.id = id;
        this.numeroTelephone = numeroTelephone;
        this.date = date;
    }

    public DemandeStage( int id,String nom, String prenom, String email, String lettremotivation, String cv, String domaine, int numeroTelephone,Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.id = id;
        this.numeroTelephone = numeroTelephone;
        this.date = date;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, int numeroTelephone,Date date,int score) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.numeroTelephone = numeroTelephone;
        this.date = date;
        this.score = score;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, int numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.numeroTelephone = numeroTelephone;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine,int numeroTelephone, String etat,  Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.date = date;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLettremotivation() {
        return lettremotivation;
    }

    public void setLettremotivation(String lettremotivation) {
        this.lettremotivation = lettremotivation;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(int numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandeStage that = (DemandeStage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, email, lettremotivation, cv, domaine, etat, id, numeroTelephone, score, date);
    }

    @Override
    public String toString() {
        return "DemandeStage{" +
                "id =" +id + '\''+
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", lettremotivation='" + lettremotivation + '\'' +
                ", cv='" + cv + '\'' +
                ", domaine='" + domaine + '\'' +
                ", etat='" + etat + '\'' +
                ", id_offre=" + id_offre +
                ", numeroTelephone=" + numeroTelephone +
                ", score=" + score +
                ", date=" + date +
                '}';
    }

}
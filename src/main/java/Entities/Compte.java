package Entities;

import java.util.Date;

public class Compte {
    private  int id ;
    private  String email ;
    private String confirmation_email  ;
    private int cin;
    private Date date_delivrance_cin;
    private String nom;
    private String prenom;
    private String sexe;
    private Date date_naissance ;
    private String proffesion ;
    private String type_compte;
    private double montant;
    private String statut_marital;
    private String nationalite;
    private int numero_telephone ;
    private String preference_communic ;
    private String type_cin;
    private long rib;
    private String statut;

    public Compte(String email, String confirmation_email, int cin, Date date_delivrance_cin, String nom, String prenom, String sexe, Date date_naissance, String proffesion, String type_compte, double montant, String statut_marital, String nationalite, String preference_communic, String type_cin, String statut) {
        this.id = id;
        this.email = email;
        this.confirmation_email = confirmation_email;
        this.cin = cin;
        this.date_delivrance_cin = date_delivrance_cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.proffesion = proffesion;
        this.type_compte = type_compte;
        this.montant = montant;
        this.statut_marital = statut_marital;
        this.nationalite = nationalite;
        this.numero_telephone = numero_telephone;
        this.preference_communic = preference_communic;
        this.type_cin = type_cin;
        this.rib = rib;
        this.statut = statut;
    }

    public Compte(String email, String confirmation_email, int cin, Date date_delivrance_cin, String nom, String prenom, String sexe, Date date_naissance, String proffesion, String type_compte, double montant, String statut_marital, String nationalite, int numero_telephone, String type_cin, String statut) {
        this.email = email;
        this.confirmation_email = confirmation_email;
        this.cin = cin;
        this.date_delivrance_cin = date_delivrance_cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.proffesion = proffesion;
        this.type_compte = type_compte;
        this.montant = montant;
        this.statut_marital = statut_marital;
        this.nationalite = nationalite;
        this.numero_telephone = numero_telephone;
        this.type_cin = type_cin;
        this.statut = statut;
    }
    public Compte(int id,String email, String confirmation_email, int cin, Date date_delivrance_cin, String nom, String prenom, String sexe, Date date_naissance, String proffesion, String type_compte, double montant, String statut_marital, String nationalite, int numero_telephone, String type_cin, String statut) {
        this.id = id;
        this.email = email;
        this.confirmation_email = confirmation_email;
        this.cin = cin;
        this.date_delivrance_cin = date_delivrance_cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.proffesion = proffesion;
        this.type_compte = type_compte;
        this.montant = montant;
        this.statut_marital = statut_marital;
        this.nationalite = nationalite;
        this.numero_telephone = numero_telephone;
        this.type_cin = type_cin;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmation_email() {
        return confirmation_email;
    }

    public void setConfirmation_email(String confirmation_email) {
        this.confirmation_email = confirmation_email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Date getDate_delivrance_cin() {
        return date_delivrance_cin;
    }

    public void setDate_delivrance_cin(Date date_delivrance_cin) {
        this.date_delivrance_cin = date_delivrance_cin;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }



    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public String getType_compte() {
        return type_compte;
    }

    public void setType_compte(String type_compte) {
        this.type_compte = type_compte;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getStatut_marital() {
        return statut_marital;
    }

    public void setStatut_marital(String statut_marital) {
        this.statut_marital = statut_marital;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(int numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getPreference_communic() {
        return preference_communic;
    }

    public void setPreference_communic(String preference_communic) {
        this.preference_communic = preference_communic;
    }

    public String getType_cin() {
        return type_cin;
    }

    public void setType_cin(String type_cin) {
        this.type_cin = type_cin;
    }

    public long getRib() {
        return rib;
    }

    public void setRib(long rib) {
        this.rib = rib;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "compte{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", confirmation_email='" + confirmation_email + '\'' +
                ", cin=" + cin +
                ", date_delivrance_cin=" + date_delivrance_cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", date_naissance=" + date_naissance +
                ", proffesion='" + proffesion + '\'' +
                ", type_compte='" + type_compte + '\'' +
                ", montant=" + montant +
                ", statut_marital='" + statut_marital + '\'' +
                ", nationalite='" + nationalite + '\'' +
                ", numero_telephone=" + numero_telephone +
                ", preference_communic='" + preference_communic + '\'' +
                ", type_cin='" + type_cin + '\'' +
                ", rib=" + rib +
                ", statut=" + statut +
                '}';
    }
}

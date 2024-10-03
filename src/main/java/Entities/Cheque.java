package Entities;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

public class Cheque {
    private int id ;
    private int compte_id ;
    private int user_id ;
    private String beneficiaire;
    private double montant ;
    private int telephone;
    private String email;
    private int cin ;
    private String nom_prenom;
    private Date date;
    private String decision ;
    private String photo_cin;
    private String signature_id;
    private String document_id;
    private String signer_id;
    private String  pdf_sans_signature ;
    private BigDecimal rib;

    public Cheque(int id, int compte_id, int user_id, String beneficiaire, double montant, int telephone, String email, int cin, String nom_prenom, Date date, String decision, String photo_cin, String signature_id, String document_id, String signer_id, String pdf_sans_signature, BigDecimal rib) {
        this.id = id;
        this.compte_id = compte_id;
        this.user_id = user_id;
        this.beneficiaire = beneficiaire;
        this.montant = montant;
        this.telephone = telephone;
        this.email = email;
        this.cin = cin;
        this.nom_prenom = nom_prenom;
        this.date = date;
        this.decision = decision;
        this.photo_cin = photo_cin;
        this.signature_id = signature_id;
        this.document_id= document_id;
        this.signer_id = signer_id;
        this.pdf_sans_signature = pdf_sans_signature;
        this.rib = rib;
    }

    public Cheque(String beneficiaire, double montant,int telephone, String email, int cin, String nom_prenom, Date date, String photo_cin,String decision,int compte_id,int user_id,BigDecimal rib) {
        this.beneficiaire = beneficiaire;
        this.montant = montant;
        this.telephone = telephone;
        this.email = email;
        this.cin = cin;
        this.nom_prenom = nom_prenom;
        this.date = date;
        this.photo_cin = photo_cin;
        this.decision = decision;
        this.compte_id = compte_id;
        this.user_id = user_id;
        this.rib = rib;
    }

    public BigDecimal getRib() {
        return rib;
    }

    public void setRib(BigDecimal rib) {
        this.rib = rib;
    }

    public Cheque() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompte_id() {
        return compte_id;
    }

    public void setCompte_id(int compte_id) {
        this.compte_id = compte_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }


    public String getPhoto_cin() {
        return photo_cin;
    }

    public void setPhoto_cin(String photo_cin) {
        this.photo_cin = photo_cin;
    }

    public String getSignature_id() {
        return signature_id;
    }

    public void setSignature_id(String signature_id) {
        this.signature_id = signature_id;
    }

    public String getSigner_id() {
        return signer_id;
    }

    public void setSigner_id(String signer_id) {
        this.signer_id = signer_id;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getpdf_sans_signature() {
        return pdf_sans_signature;
    }

    public void setpdf_sans_signature(String pdf_sans_signature) {
        this.pdf_sans_signature = pdf_sans_signature;
    }

    @Override
    public String toString() {
        return "cheque{" +
                "id=" + id +
                ", compte_id=" + compte_id +
                ", user_id=" + user_id +
                ", beneficiaire='" + beneficiaire + '\'' +
                ", montant=" + montant +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                ", cin=" + cin +
                ", nom_prenom='" + nom_prenom + '\'' +
                ", date=" + date +
                ", decision='" + decision + '\'' +
                ", photo_cin='" + photo_cin + '\'' +
                ", signature_id='" + signature_id + '\'' +
                ", document_id='" + document_id + '\'' +
                ", signer_id='" + signer_id + '\'' +
                ", pdf_sans_signature='" + pdf_sans_signature + '\'' +
                '}';
    }
}

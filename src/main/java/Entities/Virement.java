package Entities;

import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Virement {
    private int id;
    private int compte_id;
    private int user_id ;
    private String nomet_prenom;
    private String type_virement;
    private String transferez_a ;
    private int num_beneficiare ;
    private String montant ;
    private int cin ;
    private BigDecimal rib ;
    private String email;
    private String decision_v ;
    private String photo_cin_v ;
    private String phone_number ;

    private String qrCode ;
    public Virement(){}

    public Virement(int id, int compteId, int userId, String nometPrenom, String typeVirement, String transferezA, int numBeneficiare, String montant, int cin, BigDecimal rib, String email, String decisionV, String photoCinV, String phoneNumber, String qrCode) {
        this.id = id;
        this.compte_id = compteId;
        this.user_id = userId;
        this.nomet_prenom = nometPrenom;
        this.type_virement = typeVirement;
        this.transferez_a = transferezA;
        this.num_beneficiare = numBeneficiare;
        this.montant = montant;
        this.cin = cin;
        this.rib = rib;
        this.email = email;
        this.decision_v = decisionV;
        this.photo_cin_v = photoCinV;
        this.phone_number = phoneNumber;
        this.qrCode = qrCode ;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Virement(int id, int compte_id, int user_id, String nomet_prenom, String type_virement, String transferez_a, int num_beneficiare, String montant, int cin, BigDecimal rib, String email, String decision_v, String photo_cin_v, String phone_number) {
        this.id = id;
        this.compte_id = compte_id;
        this.user_id = user_id;
        this.nomet_prenom = nomet_prenom;
        this.type_virement = type_virement;
        this.transferez_a = transferez_a;
        this.num_beneficiare = num_beneficiare;
        this.montant = montant;
        this.cin = cin;
        this.rib = rib; // Ajout du paramètre rib
        this.email = email;
        this.decision_v = decision_v;
        this.photo_cin_v = photo_cin_v;
        this.phone_number = phone_number;
    }


    public Virement(String nomet_prenom, String type_virement, String transferez_a, int num_beneficiare, String montant, int cin, BigDecimal rib, String decision_v, String photo_cin_v, String phone_number) {
        this.nomet_prenom = nomet_prenom;
        this.type_virement = type_virement;
        this.transferez_a = transferez_a;
        this.num_beneficiare = num_beneficiare;
        this.montant = montant;
        this.cin = cin;
        this.rib = rib;
        this.decision_v = decision_v;
        this.photo_cin_v = photo_cin_v;
        this.phone_number = phone_number;
    }

    // Virement virement = new Virement(montant,aa,transferez,Cin,Nom,image,decisionV);
    public Virement(String type_virement, String montant, String phone_number,String transferez_a, int cin,  String nomet_prenom ,String photo_cin_v, String decision_v ){
        this.nomet_prenom = nomet_prenom;
        this.type_virement = type_virement;
        this.transferez_a = transferez_a;
        this.montant = montant;
        this.cin = cin;
        this.decision_v = decision_v;
        this.photo_cin_v = photo_cin_v;
        this.phone_number = phone_number;
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

    public String getNomet_prenom() {
        return nomet_prenom;
    }

    public void setNomet_prenom(String nomet_prenom) {
        this.nomet_prenom = nomet_prenom;
    }

    public String getType_virement() {
        return type_virement;
    }

    public void setType_virement(String type_virement) {
        this.type_virement = type_virement;
    }

    public String getTransferez_a() {
        return transferez_a;
    }

    public void setTransferez_a(String transferez_a) {
        this.transferez_a = transferez_a;
    }

    public int getNum_beneficiare() {
        return num_beneficiare;
    }

    public void setNum_beneficiare(int num_beneficiare) {
        this.num_beneficiare = num_beneficiare;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public BigDecimal getRib() {
        return rib;
    }

    public void setRib(BigDecimal rib) {
        this.rib = rib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDecision_v() {
        return decision_v;
    }

    public void setDecision_v(String decision_v) {
        this.decision_v = decision_v;
    }


    public String getPhoto_cin_v() {
        return photo_cin_v;
    }

    public void setPhoto_cin_v(String photo_cin_v) {
        this.photo_cin_v = photo_cin_v;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "virement{" +
                "id=" + id +
                ", compte_id=" + compte_id +
                ", user_id=" + user_id +
                ", nomet_prenom='" + nomet_prenom + '\'' +
                ", type_virement='" + type_virement + '\'' +
                ", transferez_a='" + transferez_a + '\'' +
                ", num_beneficiare=" + num_beneficiare +
                ", montant=" + montant +
                ", cin=" + cin +
                ", rib=" + rib +
                ", email='" + email + '\'' +
                ", decision_v='" + decision_v + '\'' +
                ", photo_cin_v='" + photo_cin_v + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

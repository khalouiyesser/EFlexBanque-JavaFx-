package Entities;

import java.sql.Time;
import java.util.Date;

public class RDV {
    private int id;

    private int credit_id ;

    public RDV() {

    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    private int idclient;

    private Time heure;

    @Override
    public String toString() {
        return "RDV{" +
                "id=" + id +
                ", credit_id=" + credit_id +
                ", idclient=" + idclient +
                ", heure=" + heure +
                ", daterdv=" + daterdv +
                ", credit=" + credit +
                ", methode='" + methode + '\'' +
                ", employename='" + employename + '\'' +
                '}';
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    private Date daterdv;
    private Credit credit; // Reference to the Credit object


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(int credit_id) {
        this.credit_id = credit_id;
    }


    public RDV(int id, int credit_id, int idclient, Time heure, Date daterdv, String methode, String employename) {
        this.id = id;
        this.credit_id = credit_id;
        this.idclient = idclient;
        this.heure = heure;
        this.daterdv = daterdv;
        this.methode = methode;
        this.employename = employename;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public Date getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(Date daterdv) {
        this.daterdv = daterdv;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

    public String getEmployename() {
        return employename;
    }

    public void setEmployename(String employename) {
        this.employename = employename;
    }

    private String methode;

    private String  employename;


}
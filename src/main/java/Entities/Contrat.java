package Entities;

import java.sql.Date;
import java.util.Objects;

public class Contrat {
    int id,id_user;
    Date date_Debut,datefin;
    String dure;

    public Contrat(){

    }
    public Contrat(int id, int id_user, Date date_Debut, Date datefin, String dure) {
        this.id = id;
        this.id_user = id_user;
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }
    public Contrat( Date date_Debut,int id, Date datefin, String dure) {
        this.id = id;
//        this.id_user = id_user;
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }


    public Contrat(int id_user, Date date_Debut, Date datefin, String dure) {
        this.id_user = id_user;
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }

    public Contrat(Date date_Debut, Date datefin, String dure) {
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate_Debut() {
        return date_Debut;
    }

    public void setDate_Debut(Date date_Debut) {
        this.date_Debut = date_Debut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getDure() {
        return dure;
    }

    public void setDure(String dure) {
        this.dure = dure;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date_Debut=" + date_Debut +
                ", datefin=" + datefin +
                ", dure='" + dure + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return id == contrat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
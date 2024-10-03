package Entities;

import java.sql.Date;
import java.util.Objects;

public class Stage {
    int id,id_user;
    String sujet;
    Date date;

    public Stage() {
    }

    public Stage(int id, int id_user, String sujet, Date date) {
        this.id = id;
        this.id_user = id_user;
        this.sujet = sujet;
        this.date = date;
    }
    public Stage( String sujet, int id, Date date) {
        this.id = id;
//        this.id_user = id_user;
        this.sujet = sujet;
        this.date = date;
    }
    public Stage(int id, String sujet){
        this.id = id;
        this.sujet = sujet;

    }

    public Stage(int id, String sujet, Date date) {
        this.id = id;
        this.sujet = sujet;
        this.date = date;
    }
    //    public Stage(int id_user, String sujet, Date date) {
//        this.id_user = id_user;
//        this.sujet = sujet;
//        this.date = date;
//    }

    public Stage(String sujet, Date date) {
        this.sujet = sujet;
        this.date = date;
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

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
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
        Stage stage = (Stage) o;
        return id == stage.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", sujet='" + sujet + '\'' +
                ", date=" + date +
                '}';
    }

}
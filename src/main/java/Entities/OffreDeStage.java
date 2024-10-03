package Entities;

import java.sql.Array;
import java.sql.Date;
import java.util.Objects;

public class OffreDeStage {

    int id;
    int user_id;
    int postePropose;
    String title,domaine,typeOffre,experience,description,exigenceOffre,pfeBook;
    Date datePostu;
    Array niveau,language,motsCles;

//,                                                            ,rs.ge,rs.getString("exigence_offre"),rs.getDate("date_postu"),rs.getArray("language"),rs.getString("pfe_book"),rs.getString("niveau"),rs.getString("exigence"));

    public OffreDeStage (int id,int user_id,int postePropose,String title,String domaine,String typeOffre,String experience,String description,String exigenceOffre,Date datePostu,Array language,String pfeBook,String niveau,String exigenceOffreyy){
        this.id = id;
        this.user_id = user_id;
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
//        this.niveau = niveau;
//        this.language = language;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
//        this.motsCles = motsCles;
        this.pfeBook = pfeBook;
        this.datePostu = datePostu;
    }

    public Array getNiveau() {
        return niveau;
    }

    public void setNiveau(Array niveau) {
        this.niveau = niveau;
    }

    public Array getLanguage() {
        return language;
    }

    public void setLanguage(Array language) {
        this.language = language;
    }

    public Array getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(Array motsCles) {
        this.motsCles = motsCles;
    }

    public OffreDeStage() {
    }
    public OffreDeStage(String title,String domaine,String type_offre,int poste_propose,String experience,/*Array language*/String description,String exigence_offre) {
        this.title = title;
        this.domaine=domaine;
        this.typeOffre = type_offre;
        this.postePropose = poste_propose;
        this.experience = experience;
//    this.niveau=niveau;
        // this.language=language;
        this.description=description;
        this.exigenceOffre=exigence_offre;
    }

    public OffreDeStage(int postePropose, String title, String domaine, String typeOffre, String experience, String description, String exigenceOffre,Date datePostu) {
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
        this.datePostu  = datePostu;
//        this.niveau = niveau;
//        this.language = language;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
    }
    public OffreDeStage(int id, int userId, int postePropose, String title, String domaine, String typeOffre, String experience, String description, String exigenceOffre, Date datePostu, Array language, String pfeBook, Array niveau) {
        this.id = id;
        this.user_id = userId;
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
        this.datePostu = datePostu;
        this.language = language;
        this.pfeBook = pfeBook;
        this.niveau = niveau;
    }

    public OffreDeStage(int id, int user_id, int postePropose, String title, String domaine, String typeOffre, String experience,  String description, String exigenceOffre,  String pfeBook, Date datePostu) {
        this.id = id;
        this.user_id = user_id;
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
        this.niveau = niveau;
        this.language = language;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
        this.motsCles = motsCles;
        this.pfeBook = pfeBook;
        this.datePostu = datePostu;
    }
    public OffreDeStage(int id, int postePropose, String title, String domaine, String typeOffre, String experience,  String description, String exigenceOffre, Date datePostu) {
        this.id = id;
//        this.user_id = user_id;
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
//        this.niveau = niveau;
//        this.language = language;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
//        this.motsCles = motsCles;
//        this.pfeBook = pfeBook;
        this.datePostu = datePostu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPostePropose() {
        return postePropose;
    }

    public void setPostePropose(int postePropose) {
        this.postePropose = postePropose;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

//    public String getNiveau() {
//        return niveau;
//    }
//
//    public void setNiveau(String niveau) {
//        this.niveau = niveau;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public void setLanguage(String language) {
//        this.language = language;
//    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExigenceOffre() {
        return exigenceOffre;
    }

    public void setExigenceOffre(String exigenceOffre) {
        this.exigenceOffre = exigenceOffre;
    }

//    public String getMotsCles() {
//        return motsCles;
//    }
//
//    public void setMotsCles(String motsCles) {
//        this.motsCles = motsCles;
//    }

    public String getPfeBook() {
        return pfeBook;
    }

    public void setPfeBook(String pfeBook) {
        this.pfeBook = pfeBook;
    }

    public Date getDatePostu() {
        return datePostu;
    }

    public void setDatePostu(Date datePostu) {
        this.datePostu = datePostu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffreDeStage that = (OffreDeStage) o;
        return id == that.id;
    }
//    public boolean equalss(int a){
//        if (this == a) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        OffreDeStage that = (OffreDeStage) o;
//        return id == that.id;
//        return true;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OffreDeStage{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", postePropose=" + postePropose +
                ", title='" + title + '\'' +
                ", domaine='" + domaine + '\'' +
                ", typeOffre='" + typeOffre + '\'' +
                ", experience='" + experience + '\'' +
                ", description='" + description + '\'' +
                ", exigenceOffre='" + exigenceOffre + '\'' +
                ", pfeBook='" + pfeBook + '\'' +
                ", datePostu=" + datePostu +
                ", niveau=" + niveau +
                ", language=" + language +
                ", motsCles=" + motsCles +
                '}';
    }
}
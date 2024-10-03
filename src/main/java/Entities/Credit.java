package Entities;

import java.util.*;

public class Credit {
    private int id;
    private int id_client;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getStatusclient() {
        return statusclient;
    }

    public Credit(int id, int id_client, double montant, String statusclient, double taux, Date datedebut, double mensualite, int duree, double fraisretard) {
        this.id = id;
        this.id_client = id_client;
        this.montant = montant;
        this.statusclient = statusclient;
        this.taux = taux;
        this.datedebut = datedebut;
        this.mensualite = mensualite;
        this.duree = duree;
        this.fraisretard = fraisretard;
    }

    public void setStatusclient(String statusclient) {
        this.statusclient = statusclient;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    private double montant;

    private String statusclient;
    public int getId() {
        return id;
    }

    public Credit(int id, int id_client, double montant, String statusclient, double taux,String status, Date datedebut, double mensualite, int duree, double fraisretard) {
        this.id = id;
        this.id_client = id_client;

        this.montant = montant;
        this.statusclient = statusclient;
        this.mensualite = mensualite;

        this.taux = taux;
        this.status = status;
        this.datedebut = datedebut;
        this.duree = duree;
        this.fraisretard = fraisretard;
    }



    public Map<Integer, Integer> countCreditsByYear(List<Credit> credits) {
        Map<Integer, Integer> creditCountByYear = new HashMap<>();

        // Iterate through the list of credits
        for (Credit credit : credits) {
            // Extract the year from the credit's start date
            int year = getYearFromDate(credit.getDateDebut());

            // Increment the count for the corresponding year in the map
            creditCountByYear.put(year, creditCountByYear.getOrDefault(year, 0) + 1);
        }

        return creditCountByYear;
    }
    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public Date getDateDebut() {
        return datedebut;
    }

    public void setDateDebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getFraisretard() {
        return fraisretard;
    }

    public void setFraisretard(double fraisretard) {
        this.fraisretard = fraisretard;
    }

    public Double getFraisRetard() {
        return fraisretard;
    }

    public void setFraisRetard(double fraisretard) {
        this.fraisretard = fraisretard;
    }

    private double taux;
    private Date datedebut;

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", id_client=" + id_client +
                ", status='" + status + '\'' +
                ", montant=" + montant +
                ", statusclient='" + statusclient + '\'' +
                ", taux=" + taux +
                ", datedebut=" + datedebut +
                ", mensualite=" + mensualite +
                ", duree=" + duree +
                ", fraisretard=" + fraisretard +
                '}';
    }

    private double mensualite;
    private int duree; // Durée en mois
    private double fraisretard;

}
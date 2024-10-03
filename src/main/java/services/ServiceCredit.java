package services;

import Entities.Credit;
import Entities.User;
import utils.MyDatabase;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ServiceCredit implements  IServiceCredit <Credit> {


    public static User user ;
    private Connection connection;
    public Statement statement;

    public ServiceCredit() {
        connection = MyDatabase.getInstance().getConnection();
    }


    private int getYearFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    public Map<Integer, Integer> countCreditsByYear(List<Credit> credits) {
        Map<Integer, Integer> creditCountByYear = new HashMap<>();

        // Iterate through the list of credits
        for (Credit credit : credits) {
            // Extract the year from the credit's start date
            int year = getYearFromDate((Date) credit.getDateDebut());

            // Increment the count for the corresponding year in the map
            creditCountByYear.put(year, creditCountByYear.getOrDefault(year, 0) + 1);
        }

        return creditCountByYear;
    }
    public Map<Integer, Integer> countCreditsByDuration(List<Credit> credits) {
        Map<Integer, Integer> creditCountByDuration = new HashMap<>();

        // Iterate through the list of credits
        for (Credit credit : credits) {
            int duration = credit.getDuree(); // Assuming getDuration() returns the duration of the credit

            // Increment the count for the corresponding duration in the map
            creditCountByDuration.put(duration, creditCountByDuration.getOrDefault(duration, 0) + 1);
        }

        return creditCountByDuration;
    }



    @Override
    public void supprimer(int id) throws SQLException {


    }

    @Override
    public List<Credit> afficher() throws SQLException {
        List<Credit> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM credit ";
            PreparedStatement st = connection.prepareStatement(req);
//            st.setInt(1, user.getId()); // Remplacer user.getId() par l'ID de l'utilisateur que vous souhaitez afficher les crédits
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getString("status"),
                        rs.getDate("datedebut"),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")
                ));
            }
            System.out.println("Crédits récupérés : " + list);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des crédits : " + e.getMessage());
        }
        return list;
    }
    public List<Credit> afficherClient() throws SQLException {
        List<Credit> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM credit where user_id= ? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,user.getId());
//            st.setInt(1, user.getId()); // Remplacer user.getId() par l'ID de l'utilisateur que vous souhaitez afficher les crédits
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getString("status"),
                        rs.getDate("datedebut"),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")
                ));
            }
            System.out.println("Crédits récupérés : " + list);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des crédits : " + e.getMessage());
        }
        return list;
    }

    public List<Credit> afficherAdmin() throws SQLException {
        List<Credit> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM credit";
            PreparedStatement st = connection.prepareStatement(req);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Credit credit = new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getString("status"),
                        rs.getDate("datedebut"),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")
                );
                list.add(credit);
            }
            System.out.println("Crédits récupérés : " + list);
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des crédits : " + e.getMessage());
        }
        return list;
    }


    public List<Credit> affichercreditparmontant() throws SQLException {
        List<Credit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM credit";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getObject("datedebut", Date.class),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            // Tri de la liste par montant en utilisant Java Stream
            list = list.stream()
                    .sorted(Comparator.comparingDouble(Credit::getMontant))
                    .toList();
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    public List<Credit> affichercreditpardatedebut() throws SQLException {
        List<Credit> list = new ArrayList<>();
        System.out.println("aaaaaaaaaaaaaaaaz");
        try {
            String req = "SELECT * FROM credit";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Credit(
                        rs.getInt("id"),
                        rs.getInt("id_client"),
                        rs.getInt("montant"),
                        rs.getString("statusclient"),
                        rs.getDouble("taux"),
                        rs.getObject("datedebut", Date.class),
                        rs.getDouble("mensualite"),
                        rs.getInt("duree"),
                        rs.getDouble("fraisretard")));
            }
            // Tri de la liste par date de début en utilisant Java Stream
            list = list.stream()
                    .sorted(Comparator.comparing(Credit::getDatedebut))
                    .toList();
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

}

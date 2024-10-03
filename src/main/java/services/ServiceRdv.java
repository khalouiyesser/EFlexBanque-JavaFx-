package services;

import Entities.RDV;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRdv implements  IServiceRdv <RDV> {
    private Connection connection;
    public Statement statement;

    public ServiceRdv() {
        connection = MyDatabase.getInstance().getConnection();
    }





    @Override
    public void supprimer(int id) throws SQLException {


    }
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    @Override
    public List  <RDV> afficher() throws SQLException {

        List<RDV> list = new ArrayList<>();
        String query="select id, credit_id, idclient, heure, daterdv, methode, employename from rdv";
        connection = MyDatabase.getInstance().getConnection();
        try {
            System.out.println("test servi");
            st=connection.prepareStatement(query);
            rs=st.executeQuery();
            while(rs.next()){
                RDV st=new RDV();
                st.setId(rs.getInt("id"));
                st.setCredit_id(rs.getInt("credit_id"));
                st.setIdclient(rs.getInt("idclient"));

                st.setHeure(rs.getTime("heure"));
                st.setDaterdv((rs.getDate("daterdv")));
                st.setMethode(rs.getString("methode"));
                st.setEmployename(rs.getString("employename"));
                list.add(st); // Add RDV object to the list


            }
            System.out.println(list);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}



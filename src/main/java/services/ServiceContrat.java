package services;

import Entities.Contrat;
import Entities.OffreDeStage;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceContrat implements IServiceYesser<Contrat> {
    private Connection connection;
    public ServiceContrat() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Contrat contrat) throws SQLException {
        String req = "insert into contrat " +
                "(date_debut,dure,datefin)" +
                "values(?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
//            ps.setInt(1,contrat.getId_user());
            ps.setDate(1,contrat.getDate_Debut());
            ps.setString(2,contrat.getDure());
            ps.setDate(3,contrat.getDatefin());
//            ps.setInt(3,stage.getId_user());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from contrat where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Contrat avec ID " + id + " est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Contrat contrat) throws SQLException {
        String req = "UPDATE contrat SET date_debut = ?,dure = ?, datefin=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(2, contrat.getDure());
            ps.setDate(1, new java.sql.Date(contrat.getDate_Debut().getTime())); // Conversion de java.util.Date à java.sql.Date
            ps.setDate(3, new java.sql.Date(contrat.getDatefin().getTime()));
            ps.setInt(4,contrat.getId());
            ps.executeUpdate();
            System.out.println("Modifier");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    @Override
    public List<Contrat> afficher() throws SQLException {
        List<Contrat> list = new ArrayList<>();
        try {
            String req = "select * from contrat";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Contrat(rs.getDate("date_debut"),rs.getInt("id"), rs.getDate("datefin"), rs.getString("dure")));
            }
        }catch (SQLException e){
            System.err.println("e.getMessage()");
        }
        for (Contrat i :list){
            System.out.println("Stage: id = "+i.getId()+ " "
                    + "date_debut = " + i.getDate_Debut() + " "
                    + "dure = "+ i.getDure() + ""
                    + " date fin " + i.getDatefin()
            );
        }
        return list;

    }

    @Override
    public OffreDeStage afficheUne(int id) throws SQLException {

        return null;
    }
}

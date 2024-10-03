package services;

import Entities.OffreDeStage;
import Entities.Stage;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceStage implements IServiceYesser<Stage> {
    private Connection connection;
    public ServiceStage() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Stage stage) throws SQLException {
        String req = "insert into stage " +
                "(sujet,date)" +
                "values(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,stage.getSujet());
            ps.setDate(2,stage.getDate());
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
        String req = "delete from stage where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Stage avec ID "+ id + "est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Stage stage) throws SQLException {

        String req = "UPDATE stage SET sujet=?, date=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, stage.getSujet());
            ps.setDate(2, new java.sql.Date(stage.getDate().getTime())); // Conversion de java.util.Date à java.sql.Date
            ps.setInt(3, stage.getId());
            ps.executeUpdate();
            System.out.println("Modifier");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<Stage> afficher() throws SQLException {
        List<Stage> list = new ArrayList<>();
        try {
            String req = "select * from stage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Stage(rs.getInt("id"),rs.getString("sujet"),rs.getDate("date")));
            }
        }catch (SQLException e){
            System.err.println("e.getMessage()");
        }
        for (Stage i :list){
            System.out.println("Stage: id = "+i.getId()+ " "
                    + "Sujet = " + i.getSujet() + " "
                    + "date = "+ i.getDate() + ""
                    );
        }
        return list;

    }

    @Override
    public OffreDeStage afficheUne(int id) throws SQLException {

        return null;
    }
}

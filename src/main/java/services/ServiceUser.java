package services;
import Entities.Reset;
import Entities.User;
import utils.MyDatabase;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser  implements IserviceUser<User> {
    static Connection connection;
    public ServiceUser(){
        connection=MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO user (email, name, roles, password, cin, date_naissance, adresse, profession, photo, is_blocked, is_verified, poste, salaire, tel,rib) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, "[\"" + user.getRoles() + "\"]");
            String salt = BCrypt.gensalt();
            String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
            preparedStatement.setString(4, hashedPassword);
            preparedStatement.setString(5, user.getCin());
            preparedStatement.setString(6, user.getDate_naissance());
            preparedStatement.setString(7, user.getPhoto());
            preparedStatement.setString(8, user.getAdresse());
            preparedStatement.setString(9, user.getProfession());
            preparedStatement.setInt(10, user.getIs_blocked());
            preparedStatement.setInt(11, user.getIs_verified());
            preparedStatement.setString(12, user.getPoste());
            preparedStatement.setInt(13, user.getSalaire());
            preparedStatement.setString(14, user.getTel());
            preparedStatement.setBigDecimal(15, new BigDecimal(user.getRib().toString())); // Convertir BigInteger en BigDecimal
            preparedStatement.executeUpdate();
            System.out.println("Utilisateur ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e);
        }
    }



    @Override
    public void modifier(User user) throws SQLException {
        String req="update user set name=?,email=?,adresse=?,cin=?,date_naissance=?,profession=?,photo=?, is_blocked=?,tel=? where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAdresse());
            preparedStatement.setString(4, user.getCin());
            preparedStatement.setString(5, user.getProfession());
            preparedStatement.setString(6, user.getDate_naissance());
            preparedStatement.setString(7, user.getPhoto());
            preparedStatement.setInt(8,user.getIs_blocked());

            preparedStatement.setString(9,user.getTel());
            preparedStatement.setString(10, user.getEmail());
           // preparedStatement.setInt(10, 12);
            preparedStatement.executeUpdate();
            System.out.println("modifie");
        }
        catch (SQLException e){
            System.out.println("Erreur lors de la modification  : " + e.getMessage());
        }


    }
    public void modifierDiconnect(User user) throws SQLException {
        String req="update user set sessionconnect=0 where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, user.getEmail());
            // preparedStatement.setInt(10, 12);
            preparedStatement.executeUpdate();
            System.out.println("modifie");
        }
        catch (SQLException e){
            System.out.println("Erreur lors de la modification  : " + e.getMessage());
        }


    }
    public void modifierConnect(User user) throws SQLException {
        String req="update user set sessionconnect=1 where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, user.getEmail());
            // preparedStatement.setInt(10, 12);
            preparedStatement.executeUpdate();
            System.out.println("modifie");
        }
        catch (SQLException e){
            System.out.println("Erreur lors de la modification  : " + e.getMessage());
        }


    }


    @Override
    public void supprimer(User user) throws SQLException {
        try {
            String req = "DELETE FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
            System.out.println("utilisateur  supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression  : " + ex.getMessage());
        }

    }

    @Override
    public List<User> afficher() throws SQLException {
        List<User>  users= new ArrayList<>();
        String req="select * from user";
        Statement statement= connection.createStatement();

        ResultSet rs= statement.executeQuery(req);
        while (rs.next()){
            User user= new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));


            users.add(user);
        }
        for (User i : users ){

            System.out.println(i.getRoles());
        }


        return users;

    }

    @Override
    public User getOneUser(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = ?";
        PreparedStatement ps =connection.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));
            user.setIs_blocked(rs.getInt("is_blocked"));
            user.setIs_verified(rs.getInt("is_verified"));
            user.setRib(BigInteger.valueOf(rs.getLong("rib")));
            user.setSessionconnect(rs.getInt("sessionconnect"));
        }
      //  ps.close();
        return user;
    }
    public User getOneUserSession() throws SQLException {
        String req = "SELECT * FROM `user` where sessionconnect = 1";
        PreparedStatement ps =connection.prepareStatement(req);
//        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));
            user.setIs_blocked(rs.getInt("is_blocked"));
            user.setIs_verified(rs.getInt("is_verified"));
            user.setRib(BigInteger.valueOf(rs.getLong("rib")));
            user.setSessionconnect(rs.getInt("sessionconnect"));
        }
        //  ps.close();
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String req = "SELECT * FROM `user` where id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id );

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));
            user.setIs_blocked(rs.getInt("is_blocked"));
            user.setIs_verified(rs.getInt("is_verified"));
        }
        ps.close();
        return user;


    }
    public ArrayList<User> getAllUser() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ? OR roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_CLIENT\"]");
        ps.setString(2, "[\"ROLE_ADMIN\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }
    public ArrayList<User> getAllClient() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_CLIENT\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public ArrayList<User> getAllEmplyee() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_EMPLYEE\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }
    @Override
    public void signUp(User user) {
        // Assurez-vous que l'email n'est pas déjà utilisé
        if (!isEmailUsed(user.getEmail())) {
            // Définir le rôle par défaut sur ROLE_CLIENT
            user.setRoles("ROLE_CLIENT");

            // Ajouter l'utilisateur en utilisant votre méthode add existante
            try {
                ajouter(user); // Méthode pour ajouter un utilisateur
                System.out.println("User signed up successfully.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Email is already used.");
        }}

    @Override
    public boolean isEmailUsed(String email) {
        // Vérifiez si l'email est déjà utilisé en consultant votre base de données
        // Vous devrez remplacer cette partie par l'accès réel à votre base de données

        // Par exemple, vous pouvez supposer qu'il y a une liste d'utilisateurs déjà enregistrés
        List<User> userList = getUsersFromDatabase(); // Méthode hypothétique pour obtenir la liste des utilisateurs depuis la base de données

        // Parcourez la liste des utilisateurs pour vérifier si l'email est déjà utilisé
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                // L'email est déjà utilisé
                return true;
            }
        }

        // Si l'email n'est pas trouvé dans la liste des utilisateurs, il n'est pas utilisé
        return false;
    }

    // Méthode fictive pour récupérer la liste des utilisateurs depuis la base de données
    private List<User> getUsersFromDatabase() {
        // Ici, vous devriez écrire le code pour récupérer les utilisateurs depuis votre base de données
        // et renvoyer la liste des utilisateurs
        // Par exemple :
        // return userDao.getAllUsers(); // userDao est une instance de votre classe DAO pour gérer les utilisateurs
        return new ArrayList<>(); // Pour l'exemple, nous retournons une liste vide
    }
    public int getActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where is_blocked = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "0");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }
    public int getunActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where is_blocked = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "1");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }

    public boolean reset(Reset t) {
        long end = System.currentTimeMillis();
        try {
            String req = "SELECT * from reset where code=?";
            PreparedStatement pst = connection.prepareStatement(req);
            pst.setInt(1, t.getCode());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                long StartTime = Long.parseLong(rs.getString("timeMils"));
                long calT = end - StartTime;
                if (calT < 120000) {
                    String email = rs.getString("email");
                    return true;
                } else {
                    String reqs = "DELETE FROM reset WHERE code=?";
                    PreparedStatement psts = connection.prepareStatement(reqs);
                    psts.setInt(1, t.getCode());
                    psts.executeUpdate();
                    System.err.println("Time OUT !! Code Introuvable.");
                    return false;
                }
            } else {
                System.err.println("Code Incorrect !");
                return false;
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        }
    }

    public void modifierMdp(String email,User t) throws SQLException {
        String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());

        try {

            System.out.println(t.getEmail()+"+"+t.getPassword());
            String reqs = "UPDATE user SET password=? WHERE email=?";
            PreparedStatement pst = connection.prepareStatement(reqs);

            pst.setString(2, email);
            pst.setString(1, hashed);
            System.out.println(t.getEmail()+"+"+hashed);

            pst.executeUpdate();

            System.out.println("Mot de passe modifié !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du mot de passe :");
            System.out.println(ex.getMessage());
            connection.rollback(); // Annuler la transaction en cas d'erreur

        }
    }

}








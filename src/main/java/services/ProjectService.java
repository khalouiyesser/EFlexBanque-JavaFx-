package services;

import Entities.Project;
import utils.DataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements IProjectService {

    public Connection conx;
    public Statement stm;

    public ProjectService() {
        conx = DataSource.getInstance().getCon();
    }

    @Override
    public void ajouter(Project project) throws SQLException {
        String req = "INSERT INTO `Project`(`nomProjet`, `img`, `categorie`, `descriptionProjet`, `budgetProjet`, `dateCreation`, `dureeProjet`, `statutProjet`,`user_id`) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, project.getNomProjet());
        ps.setString(2, project.getImg());
        ps.setString(3, project.getCategorie());
        ps.setString(4, project.getDescriptionProjet());
        ps.setDouble(5, project.getBudgetProjet());
        ps.setObject(6, project.getDateCreation());
        ps.setInt(7, project.getDureeProjet());
        ps.setInt(8, project.getStatutProjet());
        ps.setInt(9, 1);
        ps.executeUpdate();
        System.out.println("Project added successfully");
        ps.close();
    }

    @Override
    public Project getOneUser(String email) throws SQLException {
        return null;
    }

    @Override
    public Project getUserById(int email) throws SQLException {
        return null;
    }


    public void update(Project project) throws SQLException {
        String req = "UPDATE `Project` SET `nomProjet`=?, `img`=?, `categorie`=?, `descriptionProjet`=?, `budgetProjet`=?, `dateCreation`=?, `dureeProjet`=?, `statutProjet`=? WHERE id=?";

        PreparedStatement ps = DataSource.getInstance().getCon().prepareStatement(req);
        ps.setString(1, project.getNomProjet());
        ps.setString(2, project.getImg());
        ps.setString(3, project.getCategorie());
        ps.setString(4, project.getDescriptionProjet());
        ps.setDouble(5, project.getBudgetProjet());
        ps.setObject(6, project.getDateCreation());
        ps.setInt(7, project.getDureeProjet());
        ps.setInt(8, project.getStatutProjet());
        ps.setInt(9, project.getId());

        ps.executeUpdate();
        System.out.println("Project updated successfully");
        ps.close();
    }
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM project WHERE id=?";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    public Project getOneProject(String email) throws SQLException {
        String req = "SELECT * FROM `Project` WHERE email = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        Project project = null;

        if (rs.next()) {
            project = new Project();
            project.setId(rs.getInt("id"));
            project.setNomProjet(rs.getString("nomProjet"));
            project.setImg(rs.getString("img"));
            project.setCategorie(rs.getString("categorie"));
            project.setDescriptionProjet(rs.getString("descriptionProjet"));
            project.setBudgetProjet(rs.getDouble("budgetProjet"));
            project.setDateCreation(rs.getObject("dateCreation", LocalDateTime.class));
            project.setDureeProjet(rs.getInt("dureeProjet"));
            project.setStatutProjet(rs.getInt("statutProjet"));
        }

        ps.close();
        return project;
    }

    @Override
    public List<Project> afficher() throws SQLException {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM project";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Project projet = new Project();
                projet.setId(resultSet.getInt("id"));

                projet.setNomProjet(resultSet.getString("nomprojet"));
                projet.setImg(resultSet.getString("img"));
                projet.setCategorie(resultSet.getString("categorie"));
                projet.setDescriptionProjet(resultSet.getString("descriptionprojet"));
                projet.setBudgetProjet(resultSet.getDouble("budgetprojet"));
                projet.setDateCreation(resultSet.getTimestamp("datecreation").toLocalDateTime());
                projet.setDureeProjet(resultSet.getInt("dureeprojet"));
                projet.setStatutProjet(resultSet.getInt("statutprojet"));
                projets.add(projet);
            }
        }
        return projets;
    }
    public Project getProjectById(int id) throws SQLException {
        String req = "SELECT * FROM `Project` WHERE id = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Project project = null;

        if (rs.next()) {
            project = new Project();
            project.setId(rs.getInt("id"));
            project.setNomProjet(rs.getString("nomProjet"));
            project.setImg(rs.getString("img"));
            project.setCategorie(rs.getString("categorie"));
            project.setDescriptionProjet(rs.getString("descriptionProjet"));
            project.setBudgetProjet(rs.getDouble("budgetProjet"));
            project.setDateCreation(rs.getObject("dateCreation", LocalDateTime.class));
            project.setDureeProjet(rs.getInt("dureeProjet"));
            project.setStatutProjet(rs.getInt("statutProjet"));
        }

        ps.close();
        return project;
    }


    public List<Project> getAllProject() throws SQLException {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM project";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Project projet = new Project();
                projet.setId(resultSet.getInt("id"));

                projet.setNomProjet(resultSet.getString("nomprojet"));
                projet.setImg(resultSet.getString("img"));
                projet.setCategorie(resultSet.getString("categorie"));
                projet.setDescriptionProjet(resultSet.getString("descriptionprojet"));
                projet.setBudgetProjet(resultSet.getDouble("budgetprojet"));
                projet.setDateCreation(resultSet.getTimestamp("datecreation").toLocalDateTime());
                projet.setDureeProjet(resultSet.getInt("dureeprojet"));
                projet.setStatutProjet(resultSet.getInt("statutprojet"));
                projets.add(projet);
            }
        }
        return projets;
    }


}

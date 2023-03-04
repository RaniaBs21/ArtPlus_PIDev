/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;


import artplus.entities.Categorie_cours;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategorieServices implements InterfaceCategorie {

    Connection cnx;

    public CategorieServices() {
        cnx = MyConnection.getInstance().getConx();
    }

    public void ajouterCategorie() {
        try {
            String requete = "INSERT INTO categorie_cours (Nom_cat)"
                    + "VALUES ('tradition')";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Categorie ajoutée avec succès ");
        } catch (SQLException ex) {
            System.err.println("Categorie n'est pas ajoutée");

        }
    }

    public void ajouterCategorie2(Categorie_cours cat) {
        try {

            String requete2 = "INSERT INTO categorie_cours(Nom_cat)"
                    + " VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, cat.getNom_cat());
            pst.executeUpdate();
            System.out.println(" la categorie est ajoutée");

        } catch (SQLException ex) {
            System.out.println("la categorie n'est pas ajoutée");
        }
    }

    public void modifierCategorie(Categorie_cours cat) {
        try {
            String req = "UPDATE categorie_cours SET Nom_cat= '" + cat.getNom_cat() + "' WHERE Id_cat = " + cat.getId_cat();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categorie est modifiée !");
        } catch (SQLException ex) {
            System.out.println("Categorie  n'est pas modifiée !");
        }
    }

    public List<Categorie_cours> afficherCategorie() {
        List<Categorie_cours> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM categorie_cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Categorie_cours cat = new Categorie_cours();
                cat.setId_cat(rs.getInt(1));
                cat.setNom_cat(rs.getString("Nom_cat"));

                myList.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }
    public Categorie_cours get_categorie_by_id(int id) {
        Categorie_cours cat=new Categorie_cours(); 
        try {
            String requete3 = "SELECT * FROM categorie_cours where id_cat = "+id;
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                cat.setId_cat(rs.getInt(1));
                cat.setNom_cat(rs.getString("Nom_cat"));
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return cat;

    }

    public void supprimerCategorie(int Id_cat) {
        try {
            String req = "DELETE FROM categorie_cours WHERE Id_cat = " + Id_cat;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categorie est supprimée!");
        } catch (SQLException ex) {
            System.out.println("Categorie n'est pas supprimée");
        }
    }

    public List<Categorie_cours> afficherNomCategorie() {
        List<Categorie_cours> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT Nom_cat FROM categorie_cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Categorie_cours cat = new Categorie_cours();

                cat.setNom_cat(rs.getString("Nom_cat"));

                myList.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

}
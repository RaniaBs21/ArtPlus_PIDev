/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Abonement;
import artplus.entities.Cours;
import artplus.utils.MyConnection;
import static artplus.utils.MyConnection.instance;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;

public class AbonnementServices implements InterfaceAbonement {

    Connection cnx;
    private static AbonnementServices instance;

    public AbonnementServices() {
        cnx = MyConnection.getInstance().getConx();
    }

    public static AbonnementServices getInstance() {
        if (instance == null) {
            instance = new AbonnementServices();
        }
        return instance;
    }

    public void ajouterAbonement() {
        try {
            String requete = "INSERT INTO abonnement(level,cours,prix_abon,date_abon)"
                    + "VALUES ('level 1','theatre',800,'2023-02-16')";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Abonnement ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println("Abonnement  n'est pas ajouté ");

        }
    }

    public void ajouterAbonment2(Abonement ab) {
        try {

            String requete2 = "INSERT INTO abonnement(level,cours,prix_abon,date_abon)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, ab.getLevel());
            pst.setString(2, ab.getCours());
            pst.setInt(3, ab.getPrix_abon());
            pst.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));

            pst.executeUpdate();
            System.out.println(" Abonnement  est ajoutée");

        } catch (SQLException ex) {
            System.out.println("Abonnement  n'est pas ajoutée");
        }
    }

    
    public List<Abonement> afficherAbonement() {
        List<Abonement> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM abonnement";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Abonement ab = new Abonement();
                ab.setId_abon(rs.getInt(1));
                ab.setLevel(rs.getString("level"));

                ab.setCours(rs.getString("cours"));
                ab.setPrix_abon(rs.getInt("prix_abon"));
                ab.setDate_abon(rs.getDate(5));
                myList.add(ab);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

    public void supprimerAbonement(int Id_abon) {

        String req = "DELETE FROM abonnement WHERE Id_abon = " + Id_abon;
        String req2 = "SELECT FROM abonnement WHERE Id_abon = " + Id_abon;
        try {
            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setInt(1, Id_abon);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                PreparedStatement pst2 = cnx.prepareStatement(req);
                pst2.setInt(1, Id_abon);
                pst2.executeUpdate(req);
                System.out.println("Abonnement  est supprimé !");
            } else {
                System.out.println("identifiant introuvable");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierAbon(Abonement ab) {
        try {

            String reqtemp = "UPDATE abonnement SET level = ?, cours = ?, prix_abon=?  WHERE Id_abon =" + ab.getId_abon();
            PreparedStatement pst = cnx.prepareStatement(reqtemp);
            pst.setString(1, ab.getLevel());
            pst.setString(2, ab.getCours());
            pst.setInt(3, ab.getPrix_abon());

            pst.executeUpdate();
            System.out.println("abonnement modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Abonement;
import entities.Cours;
import utils.MyConnection;
import utils.MyDB;
//import static utils.MyConnection.instance;
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
        cnx = MyConnection.getInstance().getConnection();
    }

    public static AbonnementServices getInstance() {
        if (instance == null) {
            instance = new AbonnementServices();
        }
        return instance;
    }


    public void ajoutAbonement(Abonement ab) {
        try {

            String requete2 = "INSERT INTO abonnement(date_abon,id_cours,id_transaction,user)"
                    + " VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pst.setInt(2, ab.getCours().getId_c());
            pst.setInt(3, ab.getTransaction().getId());
            pst.setString(4, ab.getUser());
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
                ab.setDate_abon(rs.getDate(2));
                ab.setCours(new CoursServices().get_cours_by_id(rs.getInt(3)));
                ab.setTransaction(new ServiceTransactionIMP().get_transaction_by_id(rs.getInt(4)));
                ab.setUser(rs.getString(5));
                myList.add(ab);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

    public void supprimerAbonement(int Id_abon) {

        try {

            String req = "DELETE FROM abonnement WHERE Id_abon = " + Id_abon;

            Statement pst2 = cnx.createStatement();

            pst2.executeUpdate(req);
            System.out.println("Abonnement  est supprimé !");

            System.out.println("identifiant introuvable");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   
    @Override
    public void ajouterAbonment2(Abonement ab) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierAbon(Abonement ab) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterAbonement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}

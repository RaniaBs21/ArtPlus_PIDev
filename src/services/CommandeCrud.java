/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class CommandeCrud {

    Connection cnx;

    public CommandeCrud() {
        cnx = MyConnection.getInstance().getConnection();

    }

  public void ajouterCommande(Commande c) {
    String requete2 = "INSERT INTO commande(Pt_Red_Cmd, Date_Cmd, Date_Liv, Id_Cart) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete2);
        pst.setInt(1, c.getPt_Red_Cmd());
        pst.setDate(2, c.getDate_Cmd());
        pst.setDate(3, c.getDate_Liv());
        pst.setInt(4, c.getId_Cart());
        pst.executeUpdate();
        System.out.println("Votre commande est ajoutée");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public void supprimerCommande(Commande c) {
        
      /*  String req1="SELECT * FROM `commande` WHERE Id_Cmd=?";
        String req2 = "DELETE FROM `commande` WHERE Id_Cmd=?";

        try {
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, Id_Cmd);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){
                PreparedStatement pst2= cnx.prepareStatement(req2);
                pst2.setInt(1, Id_Cmd);
                System.out.println("Commande suuprimer avec succes");
                
            }
            else{
            System.out.println("La commande que vous voulez supprimer n'existe pas.");}

            

        } catch (SQLException ex) {

        }*/
         try {
         String requete = "DELETE FROM commande where Id_Cmd=?";
         PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setInt(1, c.getId_Cmd());
        pst.executeUpdate();
            System.out.println("Produit supprimé avec succès");
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    }

    public void modifierCommande(Commande c ,int id_c) {
        
     //   String req1="SELECT * FROM `commande` WHERE Id_Cmd=?";
        String req2= "UPDATE commande SET Pt_Red_Cmd = ?, Date_Cmd = ?, Date_Liv = ?,Id_Cart = ? WHERE Id_Cmd ="+id_c;

        try {
            PreparedStatement pst2= MyConnection.getInstance().getConnection().prepareStatement(req2);

            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setInt(1, c.getPt_Red_Cmd());
            pst.setDate(2, c.getDate_Cmd());
            pst.setDate(3, c.getDate_Liv());
            pst.setInt(4, c.getId_Cart());
           // pst.setInt(5, Id_Cmd);
            pst.executeUpdate();

            System.out.println("Modification de la commande est etablie avec Succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Commande> afficherCommande() {
        List<Commande> commandes = new ArrayList<>();
        try {
            String req;
            req = "SELECT * FROM commande";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                commandes.add(new Commande(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getInt(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commandes;

    }

}

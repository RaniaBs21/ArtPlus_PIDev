/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class ProduitCrud {

    Connection cnx;

    public ProduitCrud() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void ajouterProduit() {
        try {
            String requete = "INSERT INTO produit(Type_Prod,Description_Prod,Prix_Prod)" + "VALUES('Roman','Ekteb haja','2300')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Produit ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void ajouterProduit2(Produit p) {
        String requete2 = "INSERT INTO produit(Type_Prod,Description_Prod,Prix_Prod,Url)" + "VALUES(?,?,?,?)";
        try {

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, p.getType_Prod());
            pst.setString(2, p.getDescription_Prod());
            pst.setInt(3, p.getPrix_Prod());
              pst.setString(4,p.getUrl());
            pst.executeUpdate();
            System.out.println("Votre produit est ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  public void supprimerProduit(Produit p) {

    /*String req1 = "SELECT * FROM `produit` WHERE Id_Prod=?";
    String req2 = "DELETE FROM `produit` WHERE Id_Prod=?";

    try {
        PreparedStatement pst1 = cnx.prepareStatement(req1);
        pst1.setInt(1, Id_Prod);
        ResultSet rs = pst1.executeQuery();

        if (rs.next()) {
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            pst2.setInt(1, Id_Prod);
            pst2.executeUpdate();
            System.out.println("Produit supprimé avec succès");
        } else {
            System.out.println("Le produit que vous essayez de supprimer n'existe pas");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }*/
       try {
         String requete = "DELETE FROM produit where Id_Prod=?";
         PreparedStatement pst = MyConnection.getInstance().getConnection().prepareStatement(requete);
        pst.setInt(1, p.getId_Prod());
        pst.executeUpdate();
            System.out.println("Produit supprimé avec succès");
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

}

   public void modifierProduit(Produit p, int id_p)  {

 //   String req1 = "SELECT * FROM `produit` WHERE Id_Prod=?";
   // String req2 = "UPDATE produit SET Type_Prod = ?, Description_Prod = ?, Prix_Prod = ?,Url = ? WHERE Id_Prod ="+id_p;

    try{
            String req2 = "UPDATE produit SET Type_Prod = ?, Description_Prod = ?, Prix_Prod = ?,Url = ? WHERE Id_Prod ="+id_p;

           PreparedStatement pst2= cnx.prepareStatement(req2);
            pst2.setString(1, p.getType_Prod());
            pst2.setString(2, p.getDescription_Prod());
            pst2.setInt(3, p.getPrix_Prod());
            pst2.setString(4, p.getUrl());
         //   pst2.setInt(5, Id_Prod);
            pst2.executeUpdate();
            System.out.println("Produit modifié avec succes");
        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public List<Produit> afficherProduit() {
        List<Produit> produits = new ArrayList<>();
        try {
            String req;
            req = "SELECT * FROM produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                produits.add(new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return produits;

    }
    public Produit rechercherProduitbyid(int id) {
    String req = "SELECT * FROM produit WHERE Id_Prod= ?";
    PreparedStatement ps;
    ResultSet rs;
    Produit p = null;
    try {
        ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            p = new Produit(rs.getInt("Id_Prod"), rs.getString("Type_Prod"), rs.getString("Description_Prod"),rs.getInt("Prix_Prod"),rs.getString("Url"));
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return p;
}
       public Produit rechercherProduit() {
    String req = "SELECT * FROM produit ";
    PreparedStatement ps;
    ResultSet rs;
    Produit p = null;
    try {
        ps = cnx.prepareStatement(req);
      
        rs = ps.executeQuery();
        if (rs.next()) {
            p = new Produit(rs.getInt("Id_Prod"), rs.getString("Type_Prod"), rs.getString("Description_Prod"),rs.getInt("Prix_Prod"),rs.getString("Url"));
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return p;
}

}

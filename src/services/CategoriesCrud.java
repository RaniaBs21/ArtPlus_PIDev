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
import entities.Categories;

/**
 *
 * @author Lenovo
 */
public class CategoriesCrud {

    Connection cnx;

    public CategoriesCrud() {
        cnx = MyConnection.getInstance().getConnection();

    }

  public void ajouterCategorie(Categories c) {
    String requete2 = "INSERT INTO categorie_prod(Nom_Cat_Prod) VALUES (?)";
    try {
        PreparedStatement pst = cnx.prepareStatement(requete2);
        pst.setString(1,c.getNom_Cat_Prod());
       
        pst.executeUpdate();
        System.out.println("Votre categorie est ajoutée");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    public void supprimerCategorie(int Id_Cat_Prod) {
        
        String req1="SELECT * FROM `categorie_prod` WHERE Id_Cat_Prod=?";
        String req2 = "DELETE FROM `categorie_prod` WHERE Id_Cat_Prod=?";

        try {
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1, Id_Cat_Prod);
            ResultSet rs = pst1.executeQuery();
            if(rs.next()){
                PreparedStatement pst2= cnx.prepareStatement(req2);
                pst2.setInt(1, Id_Cat_Prod);
                System.out.println("Categorie suuprimer avec succes");
                
            }
            else{
            System.out.println("La categorie que vous voulez supprimer n'existe pas.");}

            

        } catch (SQLException ex) {

        }
    }

    public void modifierCategorie(int Id_Cat_Prod, Categories c) {
        
        String req1="SELECT * FROM `commande` WHERE Id_Cmd=?";
        String req2= "UPDATE `commande` SET  `Nom_Cat_Prod`=?  WHERE Id_Cat_Prod =?";

        try {
            PreparedStatement pst1 = cnx.prepareStatement(req1);
            pst1.setInt(1,Id_Cat_Prod);
            ResultSet rs =pst1.executeQuery();
          
            if(rs.next()){
            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setString(1, c.getNom_Cat_Prod());
            pst.setInt(2, c.getId_Cat_Prod());
          
            pst.executeUpdate();}

            System.out.println("Modification de la categorie est etablie avec Succes");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Categories> afficherCategorie() {
        List<Categories> categorie = new ArrayList<>();
        try {
            String req;
            req = "SELECT * FROM categorie_prod";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
          categorie.add(new Categories (rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categorie;

    }

}

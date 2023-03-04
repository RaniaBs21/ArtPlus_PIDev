/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Categorie_cours;
import artplus.entities.Sous_categorie;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Sous_categorieServices implements InterfaceSous_categorie {
    
    Connection  cnx;
    
    public Sous_categorieServices(){
        cnx = MyConnection.getInstance().getConx();
    }
    
    public void ajouterSous_categorie(){
        try {
            String requete = "INSERT INTO Sous_categorie(Nom_sc)"
                    + "VALUES ('peinture')";
            
            Statement ste = cnx.createStatement(); 
            ste.executeUpdate(requete);
            System.out.println("Sous_categorie ajoutée avec succès ");
        } catch (SQLException ex) {
            System.err.println("Sous_categorie n'est pas ajoutée");

        }
    }
    
     public void ajouterSous_categorie2(Sous_categorie scat){
    try {
       
            String requete2 = "INSERT INTO Sous_categorie(Nom_sc)"
                    +" VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,scat.getNom_sc());
            pst.executeUpdate();
            System.out.println(" la sous_categorie est ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("la sous_categorie n'est pas ajoutée");
        }
}
     public void modifierSous_categorie(Sous_categorie scat) {
        try {
            String req = "UPDATE sous_categorie SET Nom_sc= '" + scat.getNom_sc() + "' WHERE Id_sc = " + scat.getId_sc();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Sous_categorie est modifiée !");
        } catch (SQLException ex) {
            System.out.println("Sous_categorie n'est pas modifiée !");
        }
    }
        
    
    public List<Sous_categorie> afficherSous_Categorie(){
        List<Sous_categorie> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM sous_categorie";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Sous_categorie scat = new Sous_categorie();
                scat.setId_sc(rs.getInt(1));
                scat.setNom_sc(rs.getString("Nom_sc"));
                scat.setCategorie(new CategorieServices().get_categorie_by_id(rs.getInt("id_categorie")));
                myList.add(scat);   
            } 
        }
        catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
         return myList;
       
    }
    
    public Sous_categorie get_sous_categorie_by_id(int id) {
        PreparedStatement  pst;
        Sous_categorie sous_categorie=new Sous_categorie(); 
        try {
            String req ="select * from sous_categorie where ID_sc="+id+"";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 sous_categorie.setId_sc(rs.getInt(1));
                 sous_categorie.setNom_sc(rs.getString(2));
                 sous_categorie.setCategorie(new CategorieServices().get_categorie_by_id(rs.getInt(3))); //
                 
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return sous_categorie;
    }
    
    
               
        public void supprimerSous_categorie(int Id_sc) {
        try {
            String req = "DELETE FROM Sous_categorie WHERE Id_sc = " + Id_sc;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Sous_categorie est supprimé !");
        } catch (SQLException ex) {
            System.out.println("Sous_categorie n'est pas supprimé");
        }
    }

    
}


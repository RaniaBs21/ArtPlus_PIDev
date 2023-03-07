/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Level;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




    

public class LevelServices implements InterfaceLevel{
    Connection  cnx;
    
    public LevelServices(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterLevel(){
        try {
            String requete = "INSERT INTO level_cours (Nom_level)"
                    + "VALUES (5)";
            
            Statement ste = cnx.createStatement(); 
            ste.executeUpdate(requete);
            System.out.println("Level ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println("Level n'est pas ajoutée");

        }
    }
    
     public void ajouterLevel2(Level lev){
    try {
       
            String requete2 = "INSERT INTO level_cours(Nom_level)"
                    +" VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,lev.getNom_level());
            pst.executeUpdate();
            System.out.println(" la level est ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("la level n'est pas ajoutée");
        }
}
     public void modifierLevel(Level lev) {
        try {
            String req = "UPDATE level SET Nom_level= '" + lev.getNom_level() + "' WHERE Id_level = " + lev.getId_level();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Level est modifié !");
        } catch (SQLException ex) {
            System.out.println("Level n'est pas modifié !");
        }
    }
        
    
    public List<Level> afficherLevel(){
        List<Level> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM level_cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
               Level lev = new Level();
                lev.setId_level(rs.getInt(1));
                lev.setNom_level(rs.getString("Nom_level"));
              
                myList.add(lev);   
            } 
        }
        catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
         return myList;
       
    }
    
    
               
        public void supprimerLevel(int Id_level) {
        try {
            String req = "DELETE FROM level_cours WHERE Id_level = " + Id_level;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("level est supprimé !");
        } catch (SQLException ex) {
            System.out.println("level n'est pas supprimé");
        }
    }
        
        
   /* public List<Level> afficherNomLevel() {
        List<Level> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT Nom_level FROM level_cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
               Level lev = new Level();

                lev.setNom_level(rs.getString("Nom_level"));

                myList.add(lev);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }*/

 public List<Level> afficherNomLevel() {
        List<Level> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT Nom_level FROM level_cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Level cat = new Level();

                cat.setNom_level(rs.getString("Nom_level"));

                myList.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

    
}

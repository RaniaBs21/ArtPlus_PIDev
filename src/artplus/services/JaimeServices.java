/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;


import artplus.entities.Jaime;
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
 * @author DELL
 */
public class JaimeServices implements InterfaceJaimeServices{
    Connection  cnx;
    
    public JaimeServices(){
        cnx = MyConnection.getInstance().getConx();
    }
    
  
    @Override
    public void ajouterJaime(){
        try {
            String requete = "INSERT INTO jaime(Nbre_J)"
                    + "VALUES (5)";
            Statement ste = cnx.createStatement(); //ste va executer la requette
            ste.executeUpdate(requete);
            System.out.println("j'aime ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    @Override
     public void ajouterJaime2(Jaime j){
         try {
           // String requete2 = "INSERT INTO jaime (Nbre_J)" +" VALUES (?)";
            String requete2 = "INSERT INTO jaime " +" VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1,j.getNbre_J());
           
            pst.executeUpdate();
            System.out.println("j'aime est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         
    
    }
     
     
   @Override
    public void modifierJaime(Jaime j){
        try {
            String req = "UPDATE jaime SET Nbre_J = ? WHERE Id_J = "+j.getId_J();

            PreparedStatement pst = cnx.prepareStatement(req);

            pst.setInt(1, j.getNbre_J()); 
            pst.executeUpdate();
            System.out.println("j'aime modifié !");
        } catch (SQLException ex) {
            System.out.println("j'aime non modifié !");
            System.out.println(ex.getMessage());
        }
    }
     
   
    @Override
      public void supprimerJaime(int Id_J) {
        try {
            String req = "DELETE FROM jaime WHERE Id_J = " + Id_J;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("J'aime deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
     @Override
     public List<Jaime> afficherJaime(){
            List<Jaime> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM jaime";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Jaime c = new Jaime();
                c.setId_J(rs.getInt(1));
                c.setNbre_J(rs.getInt(2));
              

                myList.add(c);   
            }
            
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
     }
    
    
}

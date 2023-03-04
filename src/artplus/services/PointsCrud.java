/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import artplus.entities.Admin;
import artplus.entities.Points;
import artplus.entities.Question;
import artplus.entities.Quiz;
import artplus.entities.Reponse_User;
import artplus.utils.MyConnection;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nour
 */
public class PointsCrud {
       Connection cnx2;
    public void enregistrerPointsUtilisateur(Points points) {
           Points q=new Points();
           
           Admin a=new Admin();
          
            cnx2=MyConnection.getInstance().getCnx();
        try {
           
       
           String requete2="INSERT INTO points (score,id_user) VALUES ('" + q.getScore()+ "','" + q.getA()+ "'); ";
            Statement st=cnx2.createStatement();
            st.executeUpdate(requete2);
            

       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            

        }
        
    }

    // Autres méthodes de la classe ReponseUtilisateurDAO
    // ...
    }

        
        
        
        
    
    



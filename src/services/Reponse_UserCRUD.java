/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import entities.Question;
import entities.Quiz;
import entities.Reponse_User;
import utils.MyConnection;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author nour
 */
public class Reponse_UserCRUD {
       Connection cnx2;
    public void enregistrerReponseUtilisateur(Reponse_User reponse) {
           Question q=new Question();
           Quiz quiz =new Quiz();
           Admin a=new Admin();
           Reponse_User r= new Reponse_User();
            cnx2=MyConnection.getInstance().getConnection();
        try {
           
       
           String requete2="INSERT INTO reponse_utilisateur (id_quest,id_quiz,id_user,reponse) VALUES ('" + reponse.getQuestion()+ "','" + reponse.getQuiz()+ "','" + reponse.getAdmin() + "','" + reponse.getReponse()+ "'); ";
            Statement st=cnx2.createStatement();
            st.executeUpdate(requete2);
            System.out.println("votre reponse est ajoutée");

       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            

        }
        
    }

    // Autres méthodes de la classe ReponseUtilisateurDAO
    // ...
    }

        
        
        
        
    
    


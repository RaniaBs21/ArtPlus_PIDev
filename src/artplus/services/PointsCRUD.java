/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*package artplus.services;
import artplus.entities.Points;
import java.time.LocalDate;
import artplus.entities.Admin;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nour
 */
/*public class PointsService {
    
    Connection cnx2;
    private int score; 
     public PointsService() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
       public int nombrePoints(int id_utilisateur){
         int res = 0;
         try {
            String requette ="SELECT * FROM points where id_utilisateur ="+id_utilisateur+";";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Points> listPoints = new ArrayList<>();
                 String req ="select count(*) FROM points WHERE id_points ="+rs.getInt("id")+";";
                  Statement stp = MyConnection.getInstance().getCnx().createStatement();
                 ResultSet rs1 =stp.executeQuery(req);
                 while (rs1.next()) {
                    res = rs1.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
         return res;
         
     }
//   //    public void dec() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Bienvenue '\" + nom_utilisateur + \"' dans le quiz !");
//
//        int score = 0;
//
//        System.out.println("Question 1 : Quelle est la capitale de la France ?");
//        System.out.println("A. Madrid");
//        System.out.println("B. Paris");
//        System.out.println("C. Rome");
//
//        String reponse1 = sc.nextLine();
//        if (reponse1.equalsIgnoreCase("B")) {
//            score++;
//            System.out.println("Bonne réponse !");
//        } else {
//            System.out.println("Mauvaise réponse !");
//        }
//
//        System.out.println("Question 2 : Quelle est la couleur du ciel ?");
//        System.out.println("A. Vert");
//        System.out.println("B. Rouge");
//        System.out.println("C. Bleu");
//
//        String reponse2 = sc.nextLine();
//        if (reponse2.equalsIgnoreCase("C")) {
//            score++;
//            System.out.println("Bonne réponse !");
//        } else {
//            System.out.println("Mauvaise réponse !");
//        }
//
//         score++;
//        //System.out.println("Fin du quiz. Votre score est de " + score + "/2.");
   
       
    public int Ajouterpoints(Points p, int score ,String classement) {
    LocalDate d = LocalDate.now();
   
    cnx2 = MyConnection.getInstance().getCnx();
    
      if (classement.equals("gagnant")) {
           System.out.println("Tu es un gagnant avec un score de " + (score + 10));
            score += 10;}
      
      if (classement.equals("perdant")) {
           System.out.println("Tu es un per avec un score de " + (score - 5));
            score -= 5;
      }
      
        try {
            String requete8 = "INSERT INTO points (score, classement, Date_Mise_A_Jour, id_utilisateur) VALUES (?,?,?,?)";
            PreparedStatement st = cnx2.prepareStatement(requete8);
            st.setInt(1, score);
            st.setString(2, classement);
            st.setString(3, String.valueOf(d));
            st.setInt(4, p.getuser());
            st.executeUpdate();
} catch (SQLException ex) {
            
          System.out.println(ex.getMessage());
    } 
        
         return p.getscore();
  
    }
   
}




      
   


    */

    
    


      
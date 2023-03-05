/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;


import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import artplus.entities.Quiz;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nour
 */
public class QuizCRUD {
       Connection cnx;
     public void ajouterQuiz(Quiz q) {
        cnx = MyConnection.getInstance().getCnx();
        try {

            String requete1 = "INSERT INTO quiz  (titre,option1,option2,option3,option4,question,reponse_correcte) VALUES ('" + q.getTitre() + "','" + q.getOption1()+ "','" + q.getOption2()+ "','" + q.getOption3()+ "','" + q.getOption4()+ "','" + q.getQuestion()+ "','" + q.getReponse_correcte()+ "'); ";
            Statement st = cnx.createStatement();

            st.executeUpdate(requete1);

            System.out.println("votre Quiz est ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }



    public void modifierQuiz (Quiz q) {
         cnx = MyConnection.getInstance().getCnx();
         String requete7 = " update quiz set titre =?,question=?,option1 = ?,option2 = ?,option3 = ?,option4 = ?,reponse_correcte = ? WHERE id_quiz =?";

        try {


            PreparedStatement preparedStmt = cnx.prepareStatement(requete7);
            preparedStmt.setInt(1, q.getId_quiz());
            preparedStmt.setString(2, q.getTitre());
            preparedStmt.setString(3, q.getQuestion());
            
            preparedStmt.setString(4, q.getOption1());
            preparedStmt.setString(5, q.getOption2());
            preparedStmt.setString(6, q.getOption3());
            preparedStmt.setString(7, q.getOption4());
            preparedStmt.setString(8, q.getReponse_correcte());

           
            preparedStmt.executeUpdate();

            {
                System.out.println("Quiz Modifiée avec succées !");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        

    }
   
    public List<Quiz> afficherQuiz() {
        List<Quiz> listQuiz = new ArrayList<>();

        try {
           

            String req2 = "SELECT * FROM quiz ;";
           

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setTitre(rs.getString(2));
                quiz.setOption1(rs.getString(3));
                quiz.setOption2(rs.getString(4));
                quiz.setOption3(rs.getString(5));
                quiz.setOption4(rs.getString(6));
                quiz.setQuestion(rs.getString(7));
                quiz.setReponse_correcte(rs.getString(7));
                
                

                listQuiz.add(quiz);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listQuiz;
    }

    public void supprimerQuiz(int id_quiz ) {
        
         cnx = MyConnection.getInstance().getCnx();
        try {

            String req = "DELETE FROM quiz WHERE id_quiz = " + id_quiz;
         

             Statement pst2 = cnx.createStatement();
        pst2.executeUpdate(req);
        System.out.println("Quiz est supprimé !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
    
    


   


    
    
}

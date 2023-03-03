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

            String requete1 = "INSERT INTO quiz  (titre) VALUES ('" + q.getTitre() + "'); ";
            Statement st = cnx.createStatement();

            st.executeUpdate(requete1);

            System.out.println("votre Quiz est ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }



    public void modifierQuiz (Quiz q) {
        cnx = MyConnection.getInstance().getCnx();
        try {

            String requete7 = " update quiz set titre = ? WHERE id_quest =?; " ;

            PreparedStatement preparedStmt =cnx.prepareStatement(requete7);


            preparedStmt.setString(1, q.getTitre());
            preparedStmt.setInt(2, q.getId_quiz());
            preparedStmt.execute();

            {
                System.out.println("Quiz Modifié avec succés !");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
   
    public List<Quiz> afficherQuiz() {
        List<Quiz> listQuiz = new ArrayList<>();

        try {
            /*String req2 = "SELECT * FROM participation ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Participation part = new Participation();
                part.setId_part(rs.getInt(1));
                UtilisateurService uS = new UtilisateurService();
                uS.findOneById(rs.getInt("Id_Ut")).getId_ut();
                //part.uS(rs.getInt("Id_Ut")));
                EvenementService evS = new EvenementService();
                evS.findOneById(rs.getInt("Id_ev")).getId_ev();
                //part.setId_ev(rs.getInt("id_ev"));
                part.setDate_participation(java.sql.Timestamp.from(java.time.Instant.now()));
             */

            String req2 = "SELECT * FROM quiz ;";
           

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId_quiz(rs.getInt(1));
                quiz.setTitre(rs.getString(2));
                

                listQuiz.add(quiz);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listQuiz;
    }

    public void supprimerQuiz(int id_quiz ) {

        try {

            String requete5 = "DELETE FROM quiz where  id_quiz = ? ; ";
            PreparedStatement ps = cnx.prepareStatement(requete5);
            ps.executeUpdate();
            {
                System.out.println("Quiz supprimé avec scuccés!");
            }
        } catch (SQLException ex) {
        }}
    


   


    
    
}

package services;

import entities.Reponse;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nour
 */
public class Reponse_QuizCRUD {

    Connection cnx2;

    public Reponse_QuizCRUD() {
        cnx2 = MyConnection.getInstance().getConnection();
    }

    public List<Reponse> AfficherReponse() {
        List<Reponse> myListreponse = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM question_quiz \n"
                    + "INNER JOIN quiz ON quiz.id_quiz = question_quiz.id_quiz \n"
                    + "INNER JOIN reponse_quiz ON question_quiz.id_quest = reponse_quiz.id_quest;";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Reponse reponse = new Reponse();
                reponse.setId_rep(rs.getInt(1));
                reponse.setOption1(rs.getString("option1"));
                reponse.setOption2(rs.getString("option2"));
                reponse.setOption3(rs.getString("option3"));
                reponse.setOption4(rs.getString("option4"));
                reponse.setReponse_correcte(rs.getString("reponse_correcte"));
                reponse.setId_quest(rs.getInt(1));
                reponse.setId_quiz(rs.getInt(2));
                myListreponse.add(reponse);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return myListreponse;

    }

    public void ajouterReponse(Reponse r) {
        cnx2 = MyConnection.getInstance().getConnection();
        try {

            String requete1 = "INSERT INTO reponse_quiz(option1,option2,option3,option4,reponse_correcte,id_quest,id_quiz) VALUES ('" + r.getOption1() + "','" + r.getOption2() + "','" + r.getOption3() + "','" + r.getOption4() + "','" + r.getReponse_correcte() + "','" + r.getId_quest() + "','" + r.getId_quiz() + "'); ";
            Statement st = cnx2.createStatement();

            st.executeUpdate(requete1);

            System.out.println("votre Reponse est ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void modifierReponse(Reponse r) {
        cnx2 = MyConnection.getInstance().getConnection();
        try {

            String requete7 = " update reponse_quiz set option1 = ?,option2 = ?,option3 = ?,option4 = ?,reponse_correcte = ?,id_quiz = ? WHERE id_rep =?; ";

            PreparedStatement preparedStmt = cnx2.prepareStatement(requete7);
            preparedStmt.setInt(1, r.getId_rep());
            preparedStmt.setString(2, r.getOption1());
            preparedStmt.setString(3, r.getOption2());
            preparedStmt.setString(4, r.getOption3());
            preparedStmt.setString(5, r.getOption4());
            preparedStmt.setString(6, r.getReponse_correcte());
            preparedStmt.setInt(7, r.getId_quiz());
            preparedStmt.setInt(7, r.getId_quest());
            preparedStmt.execute();

            {
                System.out.println("Reponse Modifiée avec succées !");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void supprimerReponse(int id_rep) {
        try {
            String req = "DELETE FROM reponse_quiz WHERE id_rep = " + id_rep;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("reponse supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

}

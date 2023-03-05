
package artplus.services;

import artplus.entities.Question;

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
 * @author nour
 */
public class Question_QuizCRUD {

    Connection cnx2;

    public Question_QuizCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
    }

   

    public void ajouterQuestion(Question q) throws SQLException {
         cnx2=MyConnection.getInstance().getCnx();
        try {
            String requete2="INSERT INTO question_quiz(desc_question,id_quiz) VALUES ('" + q.getDesc_question() + "','" + q.getId_quiz() + "'); ";
            Statement st=cnx2.createStatement();

            st.executeUpdate(requete2);
            System.out.println("votre question est ajoutée");
       
        
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            

        }
        
    }

      public List<Question> afficherQuestion() {
      List<Question> listQuestion= new ArrayList<>();
        try {
             
             String req2 = "SELECT * FROM question_quiz;";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Question question = new Question();
                question.setId_quest(rs.getInt(1));
                listQuestion.add(question);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listQuestion;
    }


       public void supprimerQuestion(int id_quest) {
        try {
            String req = "DELETE FROM question_quiz WHERE id_quest = " + id_quest;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("question supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void modifierQuestion(Question q) {
        cnx2 = MyConnection.getInstance().getCnx();
        try {
          
              String req="UPDATE question_quiz SET desc_question=?,id_quiz=? WHERE id_quest=?;";
             
            PreparedStatement preparedStmt =cnx2.prepareStatement(req);
          
            preparedStmt.setString(1,q.getDesc_question());
            preparedStmt.setInt(2,q.getId_quiz());
            preparedStmt.setInt(3,q.getId_quest());
            preparedStmt.executeUpdate();
          
              

            preparedStmt.execute();
        
            {
                System.out.println("Question Modifiée !");

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
   

      }
}

 
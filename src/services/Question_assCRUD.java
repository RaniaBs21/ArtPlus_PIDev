/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Question_ass;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

        
public class Question_assCRUD  implements InterfaceQuestion_ass {
    Connection  cnx;
    
    public  Question_assCRUD(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterquestion(){
        try {
            String requete = "INSERT INTO Question_ass(Type_Q_Ass,Description_Q_Ass)"
                    + "VALUES ('error','1',)";
            
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("question ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void ajouterquestion2(Question_ass q){
    try {
       
            String requete2 = "INSERT INTO Question_ass (Type_Q_Ass,Description_Q_Ass)"
                    +" VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,q.getType_Q_Ass());
            pst.setString(2,q.getDescription_Q_Ass());

         
            pst.executeUpdate();
            System.out.println("votre question est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     public void modifierquestion(Question_ass q) {
        try {
            String req = "UPDATE Question_ass SET Type_Q_Ass = '" + q.getType_Q_Ass() + "', Description_Q_Ass = '" + q.getDescription_Q_Ass() + "' WHERE Id_Q_Ass = " + q.getId_Q_Ass();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("question updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
                   
        public void supprimerquestion(Question_ass q) {
        String req = "DELETE FROM Question_ass WHERE Type_Q_Ass=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, q.getType_Q_Ass());
            pst.executeUpdate();
            System.out.println("Questin supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Question_ass> afficherquestion(){
        List<Question_ass> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM Question_ass";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Question_ass q = new Question_ass();
                q.setId_Q_Ass(rs.getInt(1));
                q.setType_Q_Ass(rs.getString("Type_Q_Ass"));
                q.setDescription_Q_Ass(rs.getString("Description_Q_Ass"));
                

                myList.add(q);   
            }
            
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
       
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reponse_ass;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

        
public class Reponse_assCRUD implements InterfaceReponse_ass {
    Connection  cnx;
    
    public  Reponse_assCRUD(){
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterreponse(){
        try {
            String requete = "INSERT INTO Reponse_ass(Type_Rep_Ass,Que_Rep_Ass,Description_Rep_Ass)"
                    + "VALUES ('solution,'1','description reponse')";
            
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("reponse ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void ajouterreponse2(Reponse_ass a){
    try {
       
            String requete2 = "INSERT INTO Reponse_ass (Que_Rep_Ass,Type_Rep_Ass,Description_Rep_Ass)"
                    +" VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(2,a.getType_Rep_Ass());
            pst.setString(1,a.getQue_Rep_Ass());
            pst.setString(3,a.getDescription_Rep_Ass());

         
            pst.executeUpdate();
            System.out.println("votre reponse est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
 public void modifierreponse(Reponse_ass a) {
        String req = "UPDATE  Reponse_ass SET Que_Rep_Ass=? Type_Rep_Ass=?, Description_Rep_Ass=? WHERE Id_Rep_Ass=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(3, a.getDescription_Rep_Ass());
            pst.setString(1, a.getQue_Rep_Ass());
            pst.setString(2, a.getType_Rep_Ass());
            pst.setInt(4, a.getId_Rep_Ass());
            pst.executeUpdate();
            System.out.println("reponse modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }       
                   
        public void supprimerreponse(Reponse_ass a) {
        String req = "DELETE FROM Reponse_ass WHERE Type_Rep_Ass=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, a.getType_Rep_Ass());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reponse_ass> afficherreponse(){
        List<Reponse_ass> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM Reponse_ass";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Reponse_ass a = new Reponse_ass();
                a.setId_Rep_Ass(rs.getInt(1));
                a.setType_Rep_Ass(rs.getString("Type_Rep_Ass"));
                a.setQue_Rep_Ass(rs.getString("Que_Rep_Ass"));
                a.setDescription_Rep_Ass(rs.getString("Description_Rep_Ass"));
                

                myList.add(a);   
            }
            
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
       
    }
    
}
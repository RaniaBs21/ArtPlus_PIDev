
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;
import artplus.entities.Reponse_ass;
import artplus.utils.MyConnection;
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
        cnx = MyConnection.getInstance().getConx();
    }
    
    public void ajouterreponse(){
        try {
            String requete = "INSERT INTO Reponse_ass(Type_Rep_Ass,Num_Rep_Ass,Description_Rep_Ass)"
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
       
            String requete2 = "INSERT INTO Reponse_ass (Type_Rep_Ass,Num_Rep_Ass,Description_Rep_Ass)"
                    +" VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,a.getType_Rep_Ass());
            pst.setInt(2,a.getNum_Rep_Ass());
            pst.setString(3,a.getDescription_Rep_Ass());

         
            pst.executeUpdate();
            System.out.println("votre reponse est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     public void modifierreponse(Reponse_ass a) {
        try {
            String req = "UPDATE Reponse_ass SET Type_Rep_Ass = '" + a.getType_Rep_Ass() + "', Num_Rep_Ass = '" + a.getNum_Rep_Ass() + "' WHERE Id_Q_Ass = " + a.getId_Rep_Ass();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("reponse updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
                   
        public void supprimerreponse(int Id_Rep_Ass) {
        try {
            String req = "DELETE FROM Reponse_ass WHERE Id_Rep_Ass = " + Id_Rep_Ass;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("reponse deleted !");
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
                a.setNum_Rep_Ass(rs.getInt(3));
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
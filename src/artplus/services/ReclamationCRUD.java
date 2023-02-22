/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;
import artplus.entities.Reclamation;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

        
public class ReclamationCRUD implements InterfaceReclamation {
    Connection  cnx;
    
    public  ReclamationCRUD(){
        cnx = MyConnection.getInstance().getConx();
    }
    
    public void ajouterreclamation(){
        try {
            String requete = "INSERT INTO reclamation(Num_Rec,Type_Rec,Description_Rec)"
                    + "VALUES ('1','type produit','reclamation d'un produit')";
            
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("reclamation ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void ajouterreclamation2(Reclamation r){
    try {
       
            String requete2 = "INSERT INTO reclamation (Num_Rec,Type_Rec,Description_Rec)"
                    +" VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(2,r.getType_Rec());
            pst.setString(3,r.getDescription_Rec());
            pst.setInt(1,r.getNum_Rec());

         
            pst.executeUpdate();
            System.out.println("votre reclamation est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     public void modifierreclamation(Reclamation r) {
        try {
            String req = "UPDATE reclamation SET Type_Rec = '" + r.getType_Rec() + "', Num_Q_Ass = '" + r.getNum_Rec() + "' WHERE Id_Q_Ass = " + r.getId_Rec();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
                   
        public void supprimerreclamation(int Id_Rec) {
        try {
            String req = "DELETE FROM reclamation WHERE Id_Rec = " + Id_Rec;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("reclamation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reclamation> afficherreclamation(){
        List<Reclamation> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM reclamation";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Reclamation r = new Reclamation();
                r.setId_Rec(rs.getInt(1));
                r.setType_Rec(rs.getString("Type_Rec"));
                r.setDescription_Rec(rs.getString("Description_Rec"));
                r.setNum_Rec(rs.getInt(4));
                

                myList.add(r);   
            }
            
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
       
    }
    
}
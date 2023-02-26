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
            String requete = "INSERT INTO reclamation(Type_Rec,Description_Rec)"
                    + "VALUES ('type produit','reclamation d'un produit')";
            
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("reclamation ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void ajouterreclamation2(Reclamation r){
    try {
       
            String requete2 = "INSERT INTO reclamation (Type_Rec,Description_Rec)"
                    +" VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,r.getType_Rec());
            pst.setString(2,r.getDescription_Rec());

         
            pst.executeUpdate();
            System.out.println("votre reclamation est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     public void modifierreclamation(Reclamation r) {
        try {
            String req = "UPDATE reclamation SET Type_Rec = '" + r.getType_Rec() + "', WHERE Id_Rec = " + r.getId_Rec();
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("reclamation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
                   

        public void supprimerreclamation(Reclamation r) {
        String req = "DELETE FROM Reclamation WHERE Type_Rec=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, r.getType_Rec());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<Reclamation> afficherreclamation(){
    List<Reclamation> list = new ArrayList<>();

        String req = "SELECT * FROM Reclamation";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Reclamation(result.getInt("Id_Rec"), result.getString("Type_Rec"), result.getString("Description_Rec")));
            }
            System.out.println("Reclamation récupéré");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
}
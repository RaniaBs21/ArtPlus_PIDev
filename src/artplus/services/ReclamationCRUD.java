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
            String requete = "INSERT INTO reclamation(id_user,type_Rec,Description_Rec)"
                    + "VALUES (1,'type produit','reclamation d'un produit')";
            
            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("reclamation ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    
     public void ajouterreclamation2(Reclamation r){
    try {
       
            String requete2 = "INSERT INTO reclamation (id_user,Type_Rec,Description_Rec)"
                    +" VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(3,r.getType_Rec());
            pst.setString(2,r.getDescription_Rec());
            pst.setInt(1,r.getid_user()); 
         
            pst.executeUpdate();
            System.out.println("votre reclamation est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
         
    public void modifierreclamation(Reclamation r) {
        String req = "UPDATE  reclamation SET Type_Rec=?, Description_Rec=? WHERE id_user=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(3, r.getid_user());
            pst.setString(1, r.getType_Rec());
            pst.setString(2, r.getDescription_Rec());
            pst.executeUpdate();
            System.out.println("reclamation modifiée !");
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
                list.add(new Reclamation(result.getInt("Id_Rec"),result.getInt("id_user"), result.getString("Type_Rec"), result.getString("Description_Rec")));
            }
            System.out.println("Reclamation récupéré");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        
    }
    
    public List<Reclamation> afficherSearchReclamation(String key) {
        List<Reclamation> list = new ArrayList<>();

        String req = "SELECT * from Reclamation where Reclamation.Type_Rec LIKE ?";

        try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, key + "%");
        ResultSet result = pst.executeQuery();
        while (result.next()) {
            list.add(new Reclamation(result.getInt("Id_Rec"),result.getInt("id_user"), result.getString("Type_Rec"), result.getString("Description_Rec")));
        }
        System.out.println("reclamation recherche récupérées !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
    }


    
}
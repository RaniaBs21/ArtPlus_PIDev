/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Post;
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
 * @author DELL
 */
public class PostServices implements InterfacePostServices {
    Connection  cnx;
    
    public  PostServices(){
        cnx = MyConnection.getInstance().getConx();
    }
    @Override
    public void ajouterPost(){
        try {
            String requete = "INSERT INTO post(Type_Post,Description_Post,Date_Post,Heure_Post,Img_Post)"
                    + "VALUES ('statut','first post','2023-02-13','13:56','data:image/png;base64,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')";
            
            Statement ste = cnx.createStatement(); //ste va executer la requette
            ste.executeUpdate(requete);
            System.out.println("Post ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }
    @Override
     public void ajouterPost2(Post p){
    try {
       
            String requete2 = "INSERT INTO post (Type_Post,Description_Post,Date_Post,Heure_Post,Img_Post)"
                    +" VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1,p.getType_Post());
            pst.setString(2,p.getDescription_Post());
            pst.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pst.setTime(4, java.sql.Time.valueOf(java.time.LocalTime.now()));
            pst.setString(5,p.getImg_Post());
            
            pst.executeUpdate();
            System.out.println("votre post est ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
     
     @Override
    public void modifierPost(Post p)  {
        try {
            String reqModif = "UPDATE post SET Type_Post = '" + p.getType_Post() + "', description_Post = '" + p.getDescription_Post() + "', Img_Post = '" + p.getImg_Post() + "' WHERE post.`Id_Post` = " + p.getId_Post();
            Statement st = cnx.createStatement();
            st.executeUpdate(reqModif);
            System.out.println("Post updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
    
       @Override     
        public void supprimerPost(int Id_Post) {
        try {
            String req = "DELETE FROM post WHERE Id_Post = " + Id_Post;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Post deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Post> afficherPost(){
        List<Post> myList= new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM post";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()){
                Post p = new Post();
                p.setId_Post(rs.getInt(1));
                p.setType_Post(rs.getString("Type_Post"));
                p.setDescription_Post(rs.getString("Description_Post"));
                p.setDate_Post(rs.getDate(4));
                p.setHeure_Post(rs.getTime(5));
                p.setImg_Post(rs.getString("Img_Post"));
                

                myList.add(p);   
            }
            
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return myList;
       
    }
    
}

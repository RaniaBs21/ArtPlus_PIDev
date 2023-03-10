/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import utils.MyConnection;
//import static MyConnection.instance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursServices implements InterfaceCours {
    Connection cnx;
 private static CoursServices instance;

    public CoursServices() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public static CoursServices getInstance() {
        if (instance == null) {
            instance = new CoursServices();
        }
        return instance;
    }

   
    

    public void ajouterCours() {
        try {
            String requete = "INSERT INTO cours (Titre_c,Sous_categorie,Niveau_c,Fichier_c,Description_c,date_c)"
                    + "VALUES ('introoo','culture',3,'vvvvv','cours integré','2023-02-16')";

            Statement ste = cnx.createStatement();
            ste.executeUpdate(requete);
            System.out.println("Cours ajouté avec succès ");
        } catch (SQLException ex) {
            System.err.println("cours n'est pas ajouté ");

        }
    }

    public void ajouterCours2(Cours c) {
        try {

            String requete2 = "INSERT INTO cours(Titre_c,Sous_categorie,Niveau_c,Fichier_c,Description_c,date_c,prix)"
                    + " VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, c.getTitre_c()); 
            System.out.println("sous cat id "+c.getSous_categorie().getId_sc());
            pst.setInt(2, c.getSous_categorie().getId_sc());
            pst.setInt(3, c.getNiveau_c());
            pst.setBytes(4, c.getFichier_c());
            pst.setString(5, c.getDescription_c());
            pst.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pst.setFloat(7, c.getPrix());

            pst.executeUpdate();
            System.out.println(" le cours est ajoutée");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("le cours n'est pas ajoutée");
        }
    }
    
    public void update_cours(Cours cours) {
            String req = "UPDATE cours SET Titre_c = ? , Sous_categorie = ? , Niveau_c = ? , Fichier_c = ? , Description_c = ?, prix = ? ,  where Id_c = ?";
            PreparedStatement  pst;
            try {
                    pst = cnx.prepareStatement(req); 
                    pst.setString(1, cours.getTitre_c());
                    pst.setInt(2, cours.getSous_categorie().getId_sc());
                    pst.setInt(3, cours.getNiveau_c());
                    pst.setBytes(4, cours.getFichier_c());
                    pst.setString(5, cours.getDescription_c());
                    pst.setFloat(6, cours.getPrix());
                    pst.setInt(7, cours.getId_c());
                    pst.executeUpdate(); 
                    System.out.println(" cours modifié !");
            } catch (SQLException e) {
                    System.err.println(e.getMessage());
            }
        }
    public void modifierCours(Cours c) {
        try {
           
            String reqtemp = "UPDATE cours SET Titre_c = ?, Sous_categorie = ?, Niveau_c=?, Fichier_c =?, Description_c = ? WHERE Id_c ="+c.getId_c(); 
           PreparedStatement pst = cnx.prepareStatement(reqtemp);
            pst.setString(1, c.getTitre_c());
            pst.setInt(2, c.getSous_categorie().getId_sc());
            pst.setInt(3, c.getNiveau_c());
            pst.setBytes(4, c.getFichier_c());
            pst.setString(5, c.getDescription_c());
            pst.executeUpdate();
            System.out.println(" cours modifié !");
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Cours> afficherCours() {
        List<Cours> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT * FROM cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId_c(rs.getInt(1));
                c.setTitre_c(rs.getString("Titre_c"));
                c.setSous_categorie( new Sous_categorieServices().get_sous_categorie_by_id(rs.getInt("Sous_categorie"))); // obeject search 
                c.setNiveau_c(rs.getInt("Niveau_c"));
                c.setFichier_c(rs.getBytes("Fichier_c"));
                c.setDescription_c(rs.getString("Description_c"));
                c.setDate_c(rs.getDate(7));
                c.setPrix(rs.getFloat(8));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

    public Cours get_cours_by_id(int id) {
        Cours c=new Cours();
        try {

            String requete3 = "SELECT * FROM cours where Id_c = "+id;
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                c.setId_c(rs.getInt(1));
                c.setTitre_c(rs.getString("Titre_c"));
                c.setSous_categorie( new Sous_categorieServices().get_sous_categorie_by_id(rs.getInt("Sous_categorie"))); // obeject search 
                c.setNiveau_c(rs.getInt("Niveau_c"));
                c.setFichier_c(rs.getBytes("Fichier_c"));
                c.setDescription_c(rs.getString("Description_c"));
                c.setDate_c(rs.getDate(7));
                c.setPrix(rs.getFloat(8));
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return c;

    }
    
    public List<Cours> get_cours_by_categorie(int id) {
        List<Cours> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT c.* FROM cours c join sous_categorie sc ON c.Sous_categorie=sc.ID_sc join categorie_cours cc ON sc.id_categorie=cc.Id_cat where cc.Id_cat = "+id;
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId_c(rs.getInt(1));
                c.setTitre_c(rs.getString("Titre_c"));
                c.setSous_categorie( new Sous_categorieServices().get_sous_categorie_by_id(rs.getInt("Sous_categorie"))); // obeject search 
                c.setNiveau_c(rs.getInt("Niveau_c"));
                c.setFichier_c(rs.getBytes("Fichier_c"));
                c.setDescription_c(rs.getString("Description_c"));
                c.setDate_c(rs.getDate(7));
                c.setPrix(rs.getFloat(8));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }
    public void supprimerCours(int Id_c) {
        try {
            String req = "DELETE FROM cours WHERE Id_c = " + Id_c;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("cours est supprimé !");
        } catch (SQLException ex) {
            System.out.println("cours n'est pas supprimé");
        }
    }
    
    
    
    
    
    public List<Cours> rechercheCoursbyNOM(String name ){
        List<Cours> myList = new ArrayList<>();
        PreparedStatement  pst;
        int i=0; 
        try {
            
            String req ="select * from cours where Titre_c LIKE('%"+name+"%')";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                Cours c=new Cours();  
                c.setId_c(rs.getInt(1));
                c.setTitre_c(rs.getString("Titre_c"));
                c.setSous_categorie( new Sous_categorieServices().get_sous_categorie_by_id(rs.getInt("Sous_categorie"))); // obeject search 
                c.setNiveau_c(rs.getInt("Niveau_c"));
                c.setFichier_c(rs.getBytes("Fichier_c"));
                c.setDescription_c(rs.getString("Description_c"));
                c.setDate_c(rs.getDate(7));
                c.setPrix(rs.getFloat(8));
                myList.add(c);
            }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
    
 public List<Cours> afficherNomCours() {
        List<Cours> myList = new ArrayList<>();
        try {

            String requete3 = "SELECT Titre_c FROM cours";
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete3);
            while (rs.next()) {
                Cours cat = new Cours();

                cat.setTitre_c(rs.getString("Titre_c"));

                myList.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println("liste n'est pas affichée");
        }
        return myList;

    }

}






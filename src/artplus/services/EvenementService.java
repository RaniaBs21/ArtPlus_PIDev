/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Evenement;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author rahma
 */
public class EvenementService implements InterfaceEvenement<Evenement> {

    Connection cnx;

    public EvenementService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterEvenement(Evenement e) {
        try {
            String req2 = "INSERT INTO `evenement`( `titre_ev`, `categorie_ev`, `description_ev`, `image_ev`, `adresse_ev`, `date_ev`, `nbre_places`, `id_g`) " + " VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req2);
            pst.setString(1, e.getTitre_ev());
            pst.setString(2, e.getCategorie());
            pst.setString(3, e.getDescription_ev());
            pst.setString(4, e.getImage_ev());
            pst.setString(5, e.getAdresse_ev());
            pst.setTimestamp(6, e.getDateTime_ev());
            pst.setInt(7, e.getNbre_place());
            pst.setInt(8, e.getGuide().getId_guide());
            pst.executeUpdate();
            System.out.println("Evenement est ajouté avec succès! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> afficherEvenements() {
        List<Evenement> listEvenement = new ArrayList<>();

        try {
            String req3 = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                Evenement ev = new Evenement();
                ev.setId_ev(rs.getInt(1));
                ev.setTitre_ev(rs.getString("titre_ev"));
                ev.setDescription_ev(rs.getString("description_ev"));
                ev.setImage_ev(rs.getString("image_ev"));
                ev.setAdresse_ev(rs.getString("adresse_ev"));
                ev.setDateTime_ev(rs.getTimestamp("date_ev"));
                ev.setNbre_place(rs.getInt("nbre_places"));
                listEvenement.add(ev);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listEvenement;
    }

    @Override
    public void modifierEvenement(Evenement e) {
        try {
            String reqModif = "UPDATE `evenement` SET `titre_ev` = '" + e.getTitre_ev() + "', `categorie_ev` = '" + e.getCategorie() + "', `description_ev` = '" + e.getDescription_ev() + "', `image_ev` = '" + e.getImage_ev() + "', `adresse_ev` = '" + e.getAdresse_ev() + "', `date_ev` = '" + e.getDateTime_ev() + "', `nbre_places` = '" + e.getNbre_place() + "' WHERE `evenement`.`id_ev` = " + e.getId_ev();
            Statement st = cnx.createStatement();
            st.executeUpdate(reqModif);
            System.out.println("Evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE id_ev = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Evenement findOneById(int id) {
        Evenement ev = null;
        try {
            String req3 = "SELECT * FROM `guide` WHERE `id_gd` = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                ev = new Evenement();
                ev.setId_ev(rs.getInt(1));
                ev.setTitre_ev(rs.getString("titre_ev"));
                ev.setDescription_ev(rs.getString("description_ev"));
                ev.setImage_ev(rs.getString("image_ev"));
                ev.setAdresse_ev(rs.getString("adresse_ev"));
                ev.setDateTime_ev(rs.getTimestamp("date_ev"));
                ev.setNbre_place(rs.getInt("nbre_places"));
                GuideService gS = new GuideService();
                gS.findOneById(rs.getInt("id_g")).getId_guide();
                

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ev;
    }
    public Evenement searchEvenementbyCategorie(String cat ){
        String req="SELECT * FROM evenement WHERE categorie_ev=?";
        PreparedStatement ps ;
        ResultSet rs ;
        Evenement e = null ;
        try{
            ps=cnx.prepareStatement(req);
            ps.setString(1, cat );
            rs=ps.executeQuery();
            if(rs.next()){
                e = new Evenement();
                e.setId_ev(rs.getInt(1));
                e.setTitre_ev(rs.getString("titre_ev"));
                e.setDescription_ev(rs.getString("description_ev"));
                e.setImage_ev(rs.getString("image_ev"));
                e.setAdresse_ev(rs.getString("adresse_ev"));
                e.setDateTime_ev(rs.getTimestamp("date_ev"));
                e.setNbre_place(rs.getInt("nbre_places"));                        
            }
            
        }
       catch(SQLException ex) {
            System.out.println("Evenement n'est pas trouvé");
           
       } 
        return e ;
    }

}

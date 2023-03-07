/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Participation;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rahma
 */
public class ParticipationService implements InterfaceParticipation<Participation> {

    Connection cnx;

    public ParticipationService() {
        cnx = MyConnection.getInstance().getConnection();
    }

    @Override
    public void ajouterParticipation(Participation part) {
        try {
//            String req = "INSERT INTO participation(Id_Ut,id_ev,date_participation) VALUES (?,?,?)";

            String req = "INSERT INTO `participation`(`Id_Ut`, `id_ev`, `date_participation`) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, part.getUtilisateur().getId_ut());
            pst.setInt(2, part.getEvenement().getId_ev());
            pst.setTimestamp(3, part.getDate_participation());

            pst.executeUpdate();
            System.out.println("L'utilisteur est participé à l'evenement avec succès! ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        /*
        String requette = "INSERT INTO participation(Id_Ut,id_ev,date_participation) VALUES ('" + part.getUtilisateur().getId_ut() + "', '" + part.getEvenement().getId_ev()+ "', '" + part.getDate_participation()+ "'); ";
        String getPartReq = "SELECT * FROM participation where Id_Ut =" + part.getUtilisateur().getId_ut() + " AND id_ev "+ part.getEvenement().getId_ev() + ";";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(requette); 

            Statement getPart = cnx.createStatement();
            ResultSet rs = getPart.executeQuery(getPartReq);
            
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/

    }

    @Override
    public List<Participation> afficherParticipation() {
        List<Participation> listParticipation = new ArrayList<>();

        try {
            String req2 = "SELECT * FROM participation INNER JOIN utilisateur ON utilisateur.Id_Ut = participation.Id_Ut INNER JOIN evenement ON participation.id_ev = evenement.id_ev";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Participation part = new Participation();
                part.setId_part(rs.getInt(1));
                UtilisateurService uS = new UtilisateurService();
                part.setUtilisateur(uS.findOneById(rs.getInt("Id_Ut")));
                //part.uS(rs.getInt("Id_Ut")));
                EvenementService evS = new EvenementService();
                part.setEvenement(evS.findOneById(rs.getInt("id_ev")));

                //part.setId_ev(rs.getInt("id_ev"));
                part.setDate_participation(java.sql.Timestamp.from(java.time.Instant.now()));

                listParticipation.add(part);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listParticipation;
    }

    @Override
    public void modifierParticipation(Participation p) {
        try {
            String reqModif = "UPDATE `participation` SET `Id_Ut` = '" + p.getUtilisateur().getId_ut() + "', `id_ev` = '" + p.getEvenement().getId_ev() + "', `date_participation` = '" + p.getDate_participation() + "' WHERE `participation`.`id_part` = " + p.getId_part();
            Statement st = cnx.createStatement();
            st.executeUpdate(reqModif);
            System.out.println("participation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerParticipation(int id) {
        try {
            String req = "DELETE FROM `participation` WHERE id_part = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("participation supprimé avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

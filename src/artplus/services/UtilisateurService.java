/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Utilisateur;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rahma
 */
public class UtilisateurService implements InterfaceUtilisateur {

    Connection cnx;

    public UtilisateurService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public Utilisateur findOneById(int id) {
        Utilisateur utilisateur = null;
        try {
            String req3 = "SELECT * FROM `utilisateur` WHERE `Id_Ut` = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId_ut(rs.getInt(1));
                utilisateur.setNom_ut(rs.getString("Nom_Ut"));
                utilisateur.setPrenom_ut(rs.getString("Prenom_Ut"));
                utilisateur.setEmail_ut(rs.getString("Email_Ut"));
                utilisateur.setPassword_ut(rs.getString("Password_Ut"));
                utilisateur.setAdresse_ut(rs.getString("Adresse_Ut"));
                utilisateur.setNum_tel_ut(rs.getString("Num_tel_Ut"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur;
    }

}

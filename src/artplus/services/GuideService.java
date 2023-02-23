/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Guide;
import artplus.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rahma
 */
public class GuideService implements InterfaceGuide{
Connection cnx;

    public GuideService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    @Override
    public Guide findOneById(int id) {
        Guide guide = null;
        try {
            String req3 = "SELECT * FROM `guide` WHERE `id_gd` = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req3);
            while (rs.next()) {
                guide = new Guide();
                guide.setId_guide(rs.getInt(1));
                guide.setNom_guide(rs.getString("nom_gd"));
                guide.setPrenom_guide(rs.getString("prenom_gd"));
                guide.setEmail_gd(rs.getString("email_gd"));
                guide.setPassword_gd(rs.getString("password_gd"));
                guide.setAdresse_gd(rs.getString("adresse_gd"));
                guide.setNum_tel(rs.getString("num_tel_gd"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return guide;

    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

/**
 *
 * @author rahma
 */
public class Guide {
     private int id_guide;
    private String nom_guide;
    private String prenom_guide;
    private String password_gd;
    private String email_gd;
    private String adresse_gd;
    private String num_tel;

    public Guide() {
    }

    public Guide(int id_guide, String nom_guide, String prenom_guide, String password_gd, String email_gd, String adresse_gd, String num_tel) {
        this.id_guide = id_guide;
        this.nom_guide = nom_guide;
        this.prenom_guide = prenom_guide;
        this.password_gd = password_gd;
        this.email_gd = email_gd;
        this.adresse_gd = adresse_gd;
        this.num_tel = num_tel;
    }

    public Guide(String nom_guide, String prenom_guide, String password_gd, String email_gd, String adresse_gd, String num_tel) {
        this.nom_guide = nom_guide;
        this.prenom_guide = prenom_guide;
        this.password_gd = password_gd;
        this.email_gd = email_gd;
        this.adresse_gd = adresse_gd;
        this.num_tel = num_tel;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }

    public String getNom_guide() {
        return nom_guide;
    }

    public void setNom_guide(String nom_guide) {
        this.nom_guide = nom_guide;
    }

    public String getPrenom_guide() {
        return prenom_guide;
    }

    public void setPrenom_guide(String prenom_guide) {
        this.prenom_guide = prenom_guide;
    }

    public String getPassword_gd() {
        return password_gd;
    }

    public void setPassword_gd(String password_gd) {
        this.password_gd = password_gd;
    }

    public String getEmail_gd() {
        return email_gd;
    }

    public void setEmail_gd(String email_gd) {
        this.email_gd = email_gd;
    }

    public String getAdresse_gd() {
        return adresse_gd;
    }

    public void setAdresse_gd(String adresse_gd) {
        this.adresse_gd = adresse_gd;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "Guide{" + "id_guide=" + id_guide + ", nom_guide=" + nom_guide + ", prenom_guide=" + prenom_guide + ", password_gd=" + password_gd + ", email_gd=" + email_gd + ", adresse_gd=" + adresse_gd + ", num_tel=" + num_tel + '}';
    }
    
    
}

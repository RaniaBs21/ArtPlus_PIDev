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
public class Utilisateur {
    
    private int id_ut;
    private String nom_ut;
    private String prenom_ut;
    private String password_ut;
    private String email_ut;
    private String adresse_ut;
    private String num_tel_ut;
    
    public Utilisateur() {
    }

    public Utilisateur(String nom_ut, String prenom_ut, String password_ut, String email_ut, String adresse_ut, String num_tel_ut) {
        this.nom_ut = nom_ut;
        this.prenom_ut = prenom_ut;
        this.password_ut = password_ut;
        this.email_ut = email_ut;
        this.adresse_ut = adresse_ut;
        this.num_tel_ut = num_tel_ut;
    }
    

    public Utilisateur(int id_ut, String nom_ut, String prenom_ut, String password_ut, String email_ut, String adresse_ut, String num_tel_ut) {
        this.id_ut = id_ut;
        this.nom_ut = nom_ut;
        this.prenom_ut = prenom_ut;
        this.password_ut = password_ut;
        this.email_ut = email_ut;
        this.adresse_ut = adresse_ut;
        this.num_tel_ut = num_tel_ut;
    }

    public int getId_ut() {
        return id_ut;
    }

    public void setId_ut(int id_ut) {
        this.id_ut = id_ut;
    }

    public String getNom_ut() {
        return nom_ut;
    }

    public void setNom_ut(String nom_ut) {
        this.nom_ut = nom_ut;
    }

    public String getPrenom_ut() {
        return prenom_ut;
    }

    public void setPrenom_ut(String prenom_ut) {
        this.prenom_ut = prenom_ut;
    }

    public String getPassword_ut() {
        return password_ut;
    }

    public void setPassword_ut(String password_ut) {
        this.password_ut = password_ut;
    }

    public String getEmail_ut() {
        return email_ut;
    }

    public void setEmail_ut(String email_ut) {
        this.email_ut = email_ut;
    }

    public String getAdresse_ut() {
        return adresse_ut;
    }

    public void setAdresse_ut(String adresse_ut) {
        this.adresse_ut = adresse_ut;
    }

    public String getNum_tel_ut() {
        return num_tel_ut;
    }

    public void setNum_tel_ut(String num_tel_ut) {
        this.num_tel_ut = num_tel_ut;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id_ut=" + id_ut + ", nom_ut=" + nom_ut + ", prenom_ut=" + prenom_ut + ", password_ut=" + password_ut + ", email_ut=" + email_ut + ", adresse_ut=" + adresse_ut + ", num_tel_ut=" + num_tel_ut + '}';
    }
    
}

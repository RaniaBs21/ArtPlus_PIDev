/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Abonement {

    private int Id_abon ;
     //private int Id_user ;
   // private Level Id_level ;
    private String level ;
    private String cours ;
  //private String formation ;
    private int prix_abon ;
    private Date date_abon ;


    //private Utilisateur Id_user ;
    public Abonement(int Id_abon, String level, String cours, int prix_abon, Date date_abon) {
        this.Id_abon = Id_abon;
        this.level = level;
        this.cours = cours;
        this.prix_abon = prix_abon;
        this.date_abon = date_abon;
    }

    public Abonement(String level, String cours, Date date_abon) {
        this.level = level;
        this.cours = cours;
        this.date_abon = date_abon;
    }

    public Abonement(String level, String cours, int prix_abon) {
        this.level = level;
        this.cours = cours;
        this.prix_abon = prix_abon;
    }

    public Abonement(int Id_abon, String level, String cours, int prix_abon) {
        this.Id_abon = Id_abon;
        this.level = level;
        this.cours = cours;
        this.prix_abon = prix_abon;
    }

    public Abonement(String level, String cours) {
        this.level = level;
        this.cours = cours;
    }

    public Abonement() {
    }

    public int getId_abon() {
        return Id_abon;
    }

    public void setId_abon(int Id_abon) {
        this.Id_abon = Id_abon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public int getPrix_abon() {
        return prix_abon;
    }

    public void setPrix_abon(int prix_abon) {
        this.prix_abon = prix_abon;
    }

    public Date getDate_abon() {
        return date_abon;
    }

    public void setDate_abon(Date date_abon) {
        this.date_abon = date_abon;
    }

    @Override
    public String toString() {
        return "Abonement{" + "Id_abon=" + Id_abon + ", level=" + level + ", cours=" + cours + ", prix_abon=" + prix_abon + ", date_abon=" + date_abon + '}';
    }
  
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Commentaire {
    private int Id_Com;
    private String Description_Com;
    private int Nbre_Com;
    private Date Date_Com;

    public Commentaire(int Id_Com, String Description_Com, int Nbre_Com, Date Date_Com) {
        this.Id_Com = Id_Com;
        this.Description_Com = Description_Com;
        this.Nbre_Com = Nbre_Com;
        this.Date_Com = Date_Com;
    }

    public Commentaire(String Description_Com, int Nbre_Com, Date Date_Com) {
        this.Description_Com = Description_Com;
        this.Nbre_Com = Nbre_Com;
        this.Date_Com = Date_Com;
    }

    public Commentaire(int Id_Com, String Description_Com) {
        this.Id_Com = Id_Com;
        this.Description_Com = Description_Com;
    }

    public Commentaire(String Description_Com, int Nbre_Com) {
        this.Description_Com = Description_Com;
        this.Nbre_Com = Nbre_Com;
    }

    public Commentaire() {
    }
    

    public int getId_Com() {
        return Id_Com;
    }

    public void setId_Com(int Id_Com) {
        this.Id_Com = Id_Com;
    }

    public String getDescription_Com() {
        return Description_Com;
    }

    public void setDescription_Com(String Description_Com) {
        this.Description_Com = Description_Com;
    }

    public int getNbre_Com() {
        return Nbre_Com;
    }

    public void setNbre_Com(int Nbre_Com) {
        this.Nbre_Com = Nbre_Com;
    }

    public Date getDate_Com() {
        return Date_Com;
    }

    public void setDate_Com(Date Date_Com) {
        this.Date_Com = Date_Com;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "Id_Com=" + Id_Com + ", Description_Com=" + Description_Com + ", Nbre_Com=" + Nbre_Com + ", Date_Com=" + Date_Com + '}';
    }

    
}

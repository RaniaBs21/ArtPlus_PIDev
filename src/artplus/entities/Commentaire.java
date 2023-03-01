/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

import java.sql.Date;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author DELL
 */
public class Commentaire {
    private int Id_Com;
    private String Description_Com;
    private Date Date_Com;

    public Commentaire(int Id_Com, String Description_Com, Date Date_Com) {
        this.Id_Com = Id_Com;
        this.Description_Com = Description_Com;
        this.Date_Com = Date_Com;
    }

    public Commentaire(String Description_Com, Date Date_Com) {
        this.Description_Com = Description_Com;
        this.Date_Com = Date_Com;
    }

    public Commentaire(int Id_Com, String Description_Com) {
        this.Id_Com = Id_Com;
        this.Description_Com = Description_Com;
    }

    public Commentaire(String Description_Com) {
        this.Description_Com = Description_Com;
    }

    public Commentaire(Date Date_Com) {
        this.Date_Com = Date_Com;
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
    

    public Date getDate_Com() {
        return Date_Com;
    }

    public void setDate_Com(Date Date_Com) {
        this.Date_Com = Date_Com;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "Id_Com=" + Id_Com + ", Description_Com=" + Description_Com + ", Date_Com=" + Date_Com + '}';
    }

    
}
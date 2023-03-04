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
    private Date date_abon ; 
    private Cours cours;
    private Transaction transaction;
    private String user;


    public Abonement(int Id_abon, Date date_abon, Cours cours,Transaction transaction,String user) {
        this.Id_abon = Id_abon;
        this.cours = cours;
        this.date_abon = date_abon;
        this.transaction=transaction;
        this.user=user;
    }


    public Abonement() {
    }

    public int getId_abon() {
        return Id_abon;
    }

    public void setId_abon(int Id_abon) {
        this.Id_abon = Id_abon;
    }

    public Date getDate_abon() {
        return date_abon;
    }

    public void setDate_abon(Date date_abon) {
        this.date_abon = date_abon;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Abonement{" + "Id_abon=" + Id_abon + ", date_abon=" + date_abon + ", cours=" + cours + ", transaction=" + transaction + ", user=" + user + '}';
    }

    

    

    
  
}
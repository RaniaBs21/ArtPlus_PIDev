/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

import java.sql.*;

/**
 *
 * @author alaaz
 */
public class Transaction { 
    private int id; 
    private Timestamp created_At;
    private String nom_carte; 
    private String numero_carte; 
    private int exp_mois; 
    private int exp_annee; 
    private int cvc ; 
    private String paymentIntent_id; 

    public String getNom_carte() {
        return nom_carte;
    }

    public void setNom_carte(String nom_carte) {
        this.nom_carte = nom_carte;
    }

    public String getNumero_carte() {
        return numero_carte;
    }

    public void setNumero_carte(String numero_carte) {
        this.numero_carte = numero_carte;
    }

    public int getExp_mois() {
        return exp_mois;
    }

    public void setExp_mois(int exp_mois) {
        this.exp_mois = exp_mois;
    }

    public int getExp_annee() {
        return exp_annee;
    }

    public void setExp_annee(int exp_annee) {
        this.exp_annee = exp_annee;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

 
    public Transaction (){} 
    
    
    
    //constructeur de récupération des données
    public Transaction(int id, Timestamp created_At,String nom_carte,String numero_carte,int exp_mois,int exp_annee,int cvc,String paymentIntent_id) {
        this.id = id;
        this.created_At = created_At; 
        this.nom_carte=nom_carte; 
        this.numero_carte=numero_carte; 
        this.exp_mois=exp_mois; 
        this.exp_annee=exp_annee; 
        this.cvc=cvc; 
        this.paymentIntent_id=paymentIntent_id;
    }
    //constructeur d'insertion des données 
    public Transaction(String nom_carte,String numero_carte,int exp_mois,int exp_annee,int cvc,String paymentIntent_id) { 
        this.nom_carte=nom_carte; 
        this.numero_carte=numero_carte; 
        this.exp_mois=exp_mois; 
        this.exp_annee=exp_annee; 
        this.cvc=cvc; 
        this.paymentIntent_id=paymentIntent_id; 
        
    }  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Timestamp created_At) {
        this.created_At = created_At;
    }
 
     public String getPaymentIntent_id() {
        return paymentIntent_id;
    }

    public void setPaymentIntent_id(String paymentIntent_id) {
        this.paymentIntent_id = paymentIntent_id;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", created_At=" + created_At + ", nom_carte=" + nom_carte + ", numero_carte=" + numero_carte + ", exp_mois=" + exp_mois + ", exp_annee=" + exp_annee + ", cvc=" + cvc + ", paymentIntent_id=" + paymentIntent_id + '}';
    }
    

   
    
    
    
    
    
    
    
    
}

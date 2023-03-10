/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Cours {
   private int Id_c ;
   private String  Titre_c ;
   private Sous_categorie   Sous_categorie ;
    private int  Niveau_c ;
    private byte[]   Fichier_c ;
   private String   Description_c ;
    private Date date_c; 
    private float prix; 
   // private Sous_categorie Id_sc ;

    public Cours(int Id_c, String Titre_c, Sous_categorie Sous_categorie, int Niveau_c, byte[] Fichier_c, String Description_c, Date date_c,float prix) {
        this.Id_c = Id_c;
        this.Titre_c = Titre_c;
        this.Sous_categorie = Sous_categorie;
        this.Niveau_c = Niveau_c;
        this.Fichier_c = Fichier_c;
        this.Description_c = Description_c;
        this.date_c = date_c; 
        this.prix=prix;
    }

    public Cours(String Titre_c, Sous_categorie Sous_categorie, int Niveau_c, byte[] Fichier_c, String Description_c, Date date_c) {
        this.Titre_c = Titre_c;
        this.Sous_categorie = Sous_categorie;
        this.Niveau_c = Niveau_c;
        this.Fichier_c = Fichier_c;
        this.Description_c = Description_c;
        this.date_c = date_c;
    }

    public Cours(String Titre_c, Sous_categorie Sous_categorie, int Niveau_c, byte[] Fichier_c, String Description_c,float prix) {
        this.Titre_c = Titre_c;
        this.Sous_categorie = Sous_categorie;
        this.Niveau_c = Niveau_c;
        this.Fichier_c = Fichier_c;
        this.Description_c = Description_c;
        this.prix=prix;
    }

    public Cours(int Id_c, String Titre_c, Sous_categorie Sous_categorie, int Niveau_c, byte[] Fichier_c, String Description_c,float prix) {
        this.Id_c = Id_c;
        this.Titre_c = Titre_c;
        this.Sous_categorie = Sous_categorie;
        this.Niveau_c = Niveau_c;
        this.Fichier_c = Fichier_c;
        this.Description_c = Description_c;
        this.prix=prix;
    }

    public Cours() {
    }

    
    public Cours(int Id_c, String Titre_c) {
        this.Id_c = Id_c;
        this.Titre_c = Titre_c;
    }

    public int getId_c() {
        return Id_c;
    }

    public void setId_c(int Id_c) {
        this.Id_c = Id_c;
    }

    public String getTitre_c() {
        return Titre_c;
    }

    public void setTitre_c(String Titre_c) {
        this.Titre_c = Titre_c;
    }

    public Sous_categorie getSous_categorie() {
        return Sous_categorie;
    }

    public void setSous_categorie(Sous_categorie Sous_categorie) {
        this.Sous_categorie = Sous_categorie;
    }

    public int getNiveau_c() {
        return Niveau_c;
    }

    public void setNiveau_c(int Niveau_c) {
        this.Niveau_c = Niveau_c;
    }

    public byte[] getFichier_c() {
        return Fichier_c;
    }

    public void setFichier_c(byte[] Fichier_c) {
        this.Fichier_c = Fichier_c;
    }

    public String getDescription_c() {
        return Description_c;
    }

    public void setDescription_c(String Description_c) {
        this.Description_c = Description_c;
    }

    public Date getDate_c() {
        return date_c;
    }

    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    } 

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Cours{" + "Id_c=" + Id_c + ", Titre_c=" + Titre_c + ", Sous_categorie=" + Sous_categorie + ", Niveau_c=" + Niveau_c + ", Fichier_c=" + Fichier_c + ", Description_c=" + Description_c + ", date_c=" + date_c + ", prix=" + prix + '}';
    }

   

   
    
}


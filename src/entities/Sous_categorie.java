/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class Sous_categorie {
    private int Id_sc ;
    private String Nom_sc ;
    private Categorie_cours categorie ;

    public Sous_categorie(int Id_sc, String Nom_sc,Categorie_cours categorie) {
        this.Id_sc = Id_sc;
        this.Nom_sc = Nom_sc;
        this.categorie=categorie;
    }

    public Sous_categorie(String Nom_sc,Categorie_cours categorie) {
        this.Nom_sc = Nom_sc;
        this.categorie=categorie;
    }

    public Sous_categorie() {
    }
    

    

    public int getId_sc() {
        return Id_sc;
    }

    public void setId_sc(int Id_sc) {
        this.Id_sc = Id_sc;
    }

    public String getNom_sc() {
        return Nom_sc;
    }

    public void setNom_sc(String Nom_sc) {
        this.Nom_sc = Nom_sc;
    }

    public Categorie_cours getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie_cours categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Sous_categorie{" + "Id_sc=" + Id_sc + ", Nom_sc=" + Nom_sc + ", categorie=" + categorie + '}';
    }
    
    
    
}

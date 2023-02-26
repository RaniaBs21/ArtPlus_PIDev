/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

public class Categorie_cours {
    private int Id_cat ;
    private String Nom_cat;

    public Categorie_cours(int Id_cat, String Nom_cat) {
        this.Id_cat = Id_cat;
        this.Nom_cat = Nom_cat;
    }

    public Categorie_cours(String Nom_cat) {
        this.Nom_cat = Nom_cat;
    }

    public Categorie_cours() {
    }

    public int getId_cat() {
        return Id_cat;
    }

    public void setId_cat(int Id_cat) {
        this.Id_cat = Id_cat;
    }

    public String getNom_cat() {
        return Nom_cat;
    }

    public void setNom_cat(String Nom_cat) {
        this.Nom_cat = Nom_cat;
    }

    @Override
    public String toString() {
        return "Categorie_cours{" + "Id_cat=" + Id_cat + ", Nom_cat=" + Nom_cat + '}';
    }
            
    
}

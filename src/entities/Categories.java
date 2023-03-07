/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lenovo
 */
public class Categories {

    private int Id_Cat_Prod;
    private String Nom_Cat_Prod;

    public Categories(int Id_Cat_Prod, String Nom_Cat_Prod) {
        this.Id_Cat_Prod = Id_Cat_Prod;
        this.Nom_Cat_Prod = Nom_Cat_Prod;
    }

    public Categories(String Nom_Cat_Prod) {
        this.Nom_Cat_Prod = Nom_Cat_Prod;
    }

    public int getId_Cat_Prod() {
        return Id_Cat_Prod;
    }

    public void setId_Cat_Prod(int Id_Cat_Prod) {
        this.Id_Cat_Prod = Id_Cat_Prod;
    }

    public String getNom_Cat_Prod() {
        return Nom_Cat_Prod;
    }

    public void setNom_Cat_Prod(String Nom_Cat_Prod) {
        this.Nom_Cat_Prod = Nom_Cat_Prod;
    }

    @Override
    public String toString() {
        return "Categories{" + "Id_Cat_Prod=" + Id_Cat_Prod + ", Nom_Cat_Prod=" + Nom_Cat_Prod + '}';
    }

}

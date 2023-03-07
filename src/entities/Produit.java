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
public class Produit {

    static Produit valueOf(String Nom_Prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int Id_Prod;
    public String Type_Prod;
    public String Description_Prod;
    public int Prix_Prod;
    public String Url; 

    /**
     *
     */
    public Produit() {
    }

    public Produit(int Id_Prod, String Type_Prod, String Description_Prod, int Prix_Prod, String Url) {
        this.Id_Prod = Id_Prod;
        this.Type_Prod = Type_Prod;
        this.Description_Prod = Description_Prod;
        this.Prix_Prod = Prix_Prod;
        this.Url = Url;
    }

    public Produit(String Type_Prod, String Description_Prod, int Prix_Prod, String Url) {
        this.Type_Prod = Type_Prod;
        this.Description_Prod = Description_Prod;
        this.Prix_Prod = Prix_Prod;
        this.Url = Url;
    }

  

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getUrl() {
        return Url;
    }



    public int getId_Prod() {
        return Id_Prod;
    }

    public void setId_Prod(int Id_Prod) {
        this.Id_Prod = Id_Prod;
    }

    public String getType_Prod() {
        return Type_Prod;
    }

    public void setType_Prod(String Type_Prod) {
        this.Type_Prod = Type_Prod;
    }

    public String getDescription_Prod() {
        return Description_Prod;
    }

    public void setDescription_Prod(String Description_Prod) {
        this.Description_Prod = Description_Prod;
    }

    public int getPrix_Prod() {
        return Prix_Prod;
    }

    public void setPrix_Prod(int Prix_Prod) {
        this.Prix_Prod = Prix_Prod;
    }

    @Override
    public String toString() {
        return "Produit{" + "Id_Prod=" + Id_Prod + ", Type_Prod=" + Type_Prod + ", Description_Prod=" + Description_Prod + ", Prix_Prod=" + Prix_Prod + '}';
    }

}

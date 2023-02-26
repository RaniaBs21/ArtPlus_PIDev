/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

/**
 *
 * @author akrem
 */
public class Reclamation {

    
    private int Id_Rec;
    private String Description_Rec,Type_Rec;

    public Reclamation(int Id_Rec, String Description_Rec, String Type_Rec) {
        this.Id_Rec = Id_Rec;
        this.Description_Rec = Description_Rec;
        this.Type_Rec = Type_Rec;
    }

    public Reclamation(String Type_Rec, String Description_Rec) {
        this.Description_Rec = Description_Rec;
        this.Type_Rec = Type_Rec;
    }

 

     public Reclamation(){
    
    }

    public Reclamation(int i) { 
        this.Id_Rec = i;
    }

    public Reclamation(String Type_Rec) {
        this.Type_Rec = Type_Rec;
    }
    public int getId_Rec() {
        return Id_Rec;
    }

    public void setId_Rec(int Id_Rec) {
        this.Id_Rec = Id_Rec;
    }



    public String getDescription_Rec() {
        return Description_Rec;
    }

    public void setDescription_Rec(String Description_Rec) {
        this.Description_Rec = Description_Rec;
    }

    public String getType_Rec() {
        return Type_Rec;
    }

    public void setType_Rec(String Type_Rec) {
        this.Type_Rec = Type_Rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "Id_Rec=" + Id_Rec + ", Description_Rec=" + Description_Rec + ", Type_Rec=" + Type_Rec + '}';
    }

    
    }
    
    
    
    


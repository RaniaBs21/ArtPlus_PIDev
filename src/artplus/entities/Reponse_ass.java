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
public class Reponse_ass {
 private int Id_Rep_Ass;
 private String Type_Rep_Ass,Description_Rep_Ass,Que_Rep_Ass;

    public Reponse_ass(int Id_Rep_Ass, String Type_Rep_Ass, String Description_Rep_Ass, String Que_Rep_Ass) {
        this.Id_Rep_Ass = Id_Rep_Ass;
        this.Type_Rep_Ass = Type_Rep_Ass;
        this.Description_Rep_Ass = Description_Rep_Ass;
        this.Que_Rep_Ass = Que_Rep_Ass;
    }

    public Reponse_ass(String Type_Rep_Ass, String Description_Rep_Ass, String Que_Rep_Ass) {
        this.Type_Rep_Ass = Type_Rep_Ass;
        this.Description_Rep_Ass = Description_Rep_Ass;
        this.Que_Rep_Ass = Que_Rep_Ass;
    }

    public Reponse_ass(String Type_Rep_Ass) {
        this.Type_Rep_Ass = Type_Rep_Ass;
    }

  


     public Reponse_ass(){
    
    }

    public int getId_Rep_Ass() {
        return Id_Rep_Ass;
    }

    public void setId_Rep_Ass(int Id_Rep_Ass) {
        this.Id_Rep_Ass = Id_Rep_Ass;
    }

    public String getQue_Rep_Ass() {
        return Que_Rep_Ass;
    }

    public void setQue_Rep_Ass(String Que_Rep_Ass) {
        this.Que_Rep_Ass = Que_Rep_Ass;
    }

    public String getType_Rep_Ass() {
        return Type_Rep_Ass;
    }

    public void setType_Rep_Ass(String Type_Rep_Ass) {
        this.Type_Rep_Ass = Type_Rep_Ass;
    }

    public String getDescription_Rep_Ass() {
        return Description_Rep_Ass;
    }

    public void setDescription_Rep_Ass(String Description_Rep_Ass) {
        this.Description_Rep_Ass = Description_Rep_Ass;
    }

    @Override
    public String toString() {
        return "Reponse_ass{" + "Id_Rep_Ass=" + Id_Rep_Ass + ", Que_Rep_Ass=" + Que_Rep_Ass + ", Type_Rep_Ass=" + Type_Rep_Ass + ", Description_Rep_Ass=" + Description_Rep_Ass + '}';
    }


 
}

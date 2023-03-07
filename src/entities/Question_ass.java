/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author akrem
 */
public class Question_ass {
    private int Id_Q_Ass;
    private String Type_Q_Ass,Description_Q_Ass;

    public Question_ass(int Id_Q_Ass, String Type_Q_Ass, String Description_Q_Ass) {
        this.Id_Q_Ass = Id_Q_Ass;
        this.Type_Q_Ass = Type_Q_Ass;
        this.Description_Q_Ass = Description_Q_Ass;
    }

    public Question_ass(String Type_Q_Ass, String Description_Q_Ass) {
        this.Type_Q_Ass = Type_Q_Ass;
        this.Description_Q_Ass = Description_Q_Ass;
    }





   
    
    public Question_ass(){
    
    }
    public Question_ass(String Type_Q_Ass) {
        this.Type_Q_Ass = Type_Q_Ass;
    }
    public int getId_Q_Ass() {
        return Id_Q_Ass;
    }

    public void setId_Q_Ass(int Id_Q_Ass) {
        this.Id_Q_Ass = Id_Q_Ass;
    }

    public String getDescription_Q_Ass() {
        return Description_Q_Ass;
    }

    public void setDescription_Q_Ass(String Num_Q_Ass) {
        this.Description_Q_Ass = Description_Q_Ass;
    }

    public String getType_Q_Ass() {
        return Type_Q_Ass;
    }

    public void setType_Q_Ass(String Type_Q_Ass) {
        this.Type_Q_Ass = Type_Q_Ass;
    }

    @Override
    public String toString() {
        return "Question_ass{" + "Id_Q_Ass=" + Id_Q_Ass + ", Type_Q_Ass=" + Type_Q_Ass + ", Description_Q_Ass=" + Description_Q_Ass + '}';
    }




    }
    
    


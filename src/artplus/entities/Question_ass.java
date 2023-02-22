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
public class Question_ass {
    private int Id_Q_Ass,Num_Q_Ass;
    private String Type_Q_Ass;

    public Question_ass(int Id_Q_Ass, int Num_Q_Ass, String Type_Q_Ass) {
        this.Id_Q_Ass = Id_Q_Ass;
        this.Num_Q_Ass = Num_Q_Ass;
        this.Type_Q_Ass = Type_Q_Ass;
    }

    public Question_ass(int Num_Q_Ass, String Type_Q_Ass) {
        this.Num_Q_Ass = Num_Q_Ass;
        this.Type_Q_Ass = Type_Q_Ass;
    }

    public Question_ass(int Id_Q_Ass) {
        this.Id_Q_Ass = Id_Q_Ass;
    }
    
    public Question_ass(){
    
    }

    public int getId_Q_Ass() {
        return Id_Q_Ass;
    }

    public void setId_Q_Ass(int Id_Q_Ass) {
        this.Id_Q_Ass = Id_Q_Ass;
    }

    public int getNum_Q_Ass() {
        return Num_Q_Ass;
    }

    public void setNum_Q_Ass(int Num_Q_Ass) {
        this.Num_Q_Ass = Num_Q_Ass;
    }

    public String getType_Q_Ass() {
        return Type_Q_Ass;
    }

    public void setType_Q_Ass(String Type_Q_Ass) {
        this.Type_Q_Ass = Type_Q_Ass;
    }

    @Override
    public String toString() {
        return "Question_ass{" + "Id_Q_Ass=" + Id_Q_Ass + ", Num_Q_Ass=" + Num_Q_Ass + ", Type_Q_Ass=" + Type_Q_Ass + '}';
    }


    }
    
    


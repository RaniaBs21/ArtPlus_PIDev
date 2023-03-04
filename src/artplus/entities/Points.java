/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

/**
 *
 * @author nour
 */
public class Points {
    Admin a= new Admin();
    int score;
    int id_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Points() {
    }

    public Points(int score) {
        this.score = score;
    }

    public int getA() {
      
        return  getId_user();
    }

    public void setA(Admin a) {
        this.a = a;
    }
    
    
    
}

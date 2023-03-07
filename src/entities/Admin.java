/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author nour
 */
public class Admin {
    int id_user;
   
    Question q=new Question();
    Reponse r =new Reponse();
    Quiz quiz =new Quiz();

    public Admin(int id_user) {
        this.id_user = id_user;
    }

    public Admin() {
    }
    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public Reponse getR() {
        return r;
    }

    public void setR(Reponse r) {
        this.r = r;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    
    
}

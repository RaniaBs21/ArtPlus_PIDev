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
public class Reponse_User {
    int id;
    int id_user;
    int id_quest;
    String reponse;
    int id_quiz;
    Question question;
    Quiz quiz;
    Admin a;



    public Reponse_User() {
    }

    public int getQuestion() {
        return getId_quest();
        
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getAdmin() {
       return getId_user();

     
    }

    public int getQuiz() {
     return getId_quiz();
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    

    public Reponse_User(int id, int id_user, int id_quest, String reponse, int id_quiz) {
        this.id = id;
        this.id_user = id_user;
        this.id_quest = id_quest;
        this.reponse = reponse;
        this.id_quiz = id_quiz;
    }

    public Reponse_User(int id_user, int id_quest, String reponse, int id_quiz) {
        this.id_user = id_user;
        this.id_quest = id_quest;
        this.reponse = reponse;
        this.id_quiz = id_quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_quest() {
        return id_quest;
    }

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }
    
    
    }

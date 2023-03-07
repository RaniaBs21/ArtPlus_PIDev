/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;

 
/**
 *
 * @author nour
 */
public class Question {
  

    Connection cnx2;
    
   private int id_quest;
   private String desc_question;
   private int Id_quiz;
   private Quiz quiz;
   Question question;
   Quiz titre;
   
    public Question() {
    }

    
    
 
    public Question( int id_quest, String desc_question, int Id_quiz, Quiz quiz) {
       
        this.id_quest = id_quest;
        this.desc_question = desc_question; 
        this.Id_quiz = Id_quiz;
        this.quiz = quiz;
    }

    public Question(String desc_question, int Id_quiz) {
        this.desc_question = desc_question;
        this.Id_quiz = Id_quiz;
    }

    public Question(int id_quest, String desc_question, int Id_quiz) {
        this.id_quest = id_quest;
        this.desc_question = desc_question;
        this.Id_quiz = Id_quiz;
    }

    public Question(String desc_question) {
        this.desc_question = desc_question;
    }

    public Question(int id_quest) {
        this.id_quest = id_quest;
    }

  
    

    public int getId_quest() {
        return id_quest;
    }

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public String getDesc_question() {
        return desc_question;
    }

    public void setDesc_question(String desc_question) {
        this.desc_question = desc_question;
    }

    
    public int getId_quiz() {
        return Id_quiz;
    }

    public void setId_quiz(int Id_quiz) {
        this.Id_quiz = Id_quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return this.desc_question;
    }

  

}

    
    


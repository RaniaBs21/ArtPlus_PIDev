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
public class Reponse {

    private int id_rep;
    private int id_quest;
    private int id_quiz;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String reponse_correcte;
    private Quiz quiz;
    private Question question;
 

    public Reponse(String option1, String option2, String option3, String option4, String reponse_correcte) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
    }


    public Reponse(String option1, String option2, String option3, String option4, String reponse_correcte, Quiz quiz, Question question) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
        this.quiz = quiz;
        this.question = question;
    }

    public Reponse(int id_quest, int id_quiz, String option1, String option2, String option3, String option4, String reponse_correcte) {
        this.id_quest = id_quest;
        this.id_quiz = id_quiz;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
    }

    public Reponse(int id_quest, int id_quiz, String option1, String option2, String option3, String option4, String reponse_correcte, Question question) {
        this.id_quest = id_quest;
        this.id_quiz = id_quiz;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
        this.question = question;
    }

    public Reponse() {
    }

    public Reponse(int id_quiz, String option1, String option2, String option3, String option4, String reponse_correcte) {
        this.id_quiz = id_quiz;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
    }

    public Reponse(int id_rep, int id_quest, int id_quiz, String option1, String option2, String option3, String option4, String reponse_correcte) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.id_quiz = id_quiz;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.reponse_correcte = reponse_correcte;
    }

    
    
    public Reponse(int id_rep, int id_quest, int id_quiz, String option1, String option2, String option3, String option4) {
        this.id_rep = id_rep;
        this.id_quest = id_quest;
        this.id_quiz = id_quiz;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public int getId_quest() {
        return id_quest;
    }

    public void setId_quest(int id_quest) {
        this.id_quest = id_quest;
    }

    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getReponse_correcte() {
        return reponse_correcte;
    }

    public void setReponse_correcte(String reponse_correcte) {
        this.reponse_correcte = reponse_correcte;
    }

    @Override
    public String toString() {
        return "Reponse_Quiz{" + "id_rep=" + id_rep + ", id_quest=" + id_quest + ", id_quiz=" + id_quiz + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", reponse_correcte=" + reponse_correcte + '}';
    }

    /**
     * @return the Reponse
     */
    
  

}
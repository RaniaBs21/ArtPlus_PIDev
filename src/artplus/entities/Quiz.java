/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;


public class Quiz {
   
    private int id_quiz;
    private String titre;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String question;
    private String reponse_correcte;
    private Quiz quiz;

    public Quiz(int parseInt, String text, String text0) {
    }

    public Quiz(int parseInt, String text, String text0, String text1, String text2, String text3) {
    }


  
    public Quiz() {
    }

    public Quiz(int id_quiz, String titre, String option1, String option2, String option3, String option4, String question, String reponse_correcte, Quiz quiz) {
        this.id_quiz = id_quiz;
        this.titre = titre;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
        this.reponse_correcte = reponse_correcte;
        this.quiz = quiz;
    }

    public Quiz(String titre, String option1, String option2, String option3, String option4, String question, String reponse_correcte) {
        this.titre = titre;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.question = question;
        this.reponse_correcte = reponse_correcte;
       
    }

    public Quiz(int id_quiz, String titre, String question, String option1, String option2, String option3, String option4, String reponse_correcte) {
    }

    public Quiz(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getId_quiz() {
        return id_quiz;
    }

    public String getTitre() {
        return titre;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getQuestion() {
        return question;
    }

    public String getReponse_correcte() {
        return reponse_correcte;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponse_correcte(String reponse_correcte) {
        this.reponse_correcte = reponse_correcte;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    

    @Override
    public String toString() {
        return "Quiz{" + "id_quiz=" + id_quiz + ", titre=" + titre + ", option1=" + option1 + ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", question=" + question + ", reponse_correcte=" + reponse_correcte + ", quiz=" + quiz + '}';
    }

}
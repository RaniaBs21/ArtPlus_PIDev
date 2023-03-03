/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;


public class Quiz {
   
    private int id_quiz;
    private String titre;
    private Quiz quiz;

    public Quiz(int parseInt, String text, String text0) {
    }

    public Quiz(int parseInt, String text, String text0, String text1, String text2, String text3) {
    }

    public static class MetaData{
        public static final String TABLE_NAME="quiz";
        public static final String QUIZ_ID="id_quiz";
        public static final String TITRE="titre";

    }
    public Quiz() {
    }

    public Quiz(int id_quiz, String titre) {
        this.id_quiz = id_quiz;
        this.titre = titre;
    }

    public Quiz(String titre) {
        this.titre = titre;

    }


    public int getId_quiz() {
        return id_quiz;
    }

    public void setId_quiz(int id_quiz) {
        this.id_quiz = id_quiz;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return this.titre;
    }


   



}
    



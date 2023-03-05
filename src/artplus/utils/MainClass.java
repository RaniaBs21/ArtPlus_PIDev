package artplus.utils;
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */package edu.artplus.utils;
//

import artplus.entities.Question;
import artplus.entities.Quiz;
import artplus.entities.Reponse;
import artplus.services.Question_QuizCRUD;
import artplus.services.QuizCRUD;
import artplus.services.Reponse_QuizCRUD;
import java.sql.SQLException;
//
//
///**
// *
// * @author nour
// */
//
//
public class MainClass {
//
    public static void main(String[] args) throws SQLException {
        
//
//MyConnection mc =new MyConnection ();
////***********AJOUTER**********POINTS//////////////////////////////////
////  PointsService ps = new PointsService();
////Points pys = new Points(0, "", "", 1);
////int nouveauScore = ps.Ajouterpoints(pys,12, "perdant");
////
/////////////////////////////////////////////////////////////////////
//       
////*****************AJOUTER QUESTION******************
//        
QuizCRUD ques_quiz2 = new QuizCRUD();
Quiz ques_quiz = new Quiz();
ques_quiz2.supprimerQuiz(47);
////
////*************************************************************
////**********************ModifierQuestion***********************
////Question_QuizCRUD ques_quiz2 = new Question_QuizCRUD();
////Question_Quiz ques_quiz = new Question_Quiz("choix", "qui est le peinteur de ce tableau", "Art", 7, 7);
////ques_quiz2.Modifier_Question( ques_quiz);
////*************************************************************
////*********************SUPPRIMER QUESTION**********************
////Question_Quiz ques_quiz2 = new Question_Quiz(12, "gh", "fgh", "dg", 7);
////Question_QuizCRUD ques_quiz=new Question_QuizCRUD();
////ques_quiz.supprimequestion(ques_quiz2);
//
////*****************AJOUTER REPONSE*****************************
////Reponse_QuizCRUD rep_quiz = new Reponse_QuizCRUD();
////Reponse_Quiz rep_quiz2= new Reponse("choix", "PabloPicasso", "A", 4, "2000/02/03", 2);
////rep_quiz.ajouterReponse2(rep_quiz2);
////***************************************************************
//
//
//
//// *******************MODIFIERREPONSE****************************
// //Reponse_QuizCRUD rep_quiz = new Reponse_QuizCRUD();
////Reponse_Quiz rep_quiz2= new Reponse("jj", "desc_rep", "rep_choisie", 4, "date_rep", 7);
////       rep_quiz.Modifier_Reponse(rep_quiz2);
//////  
//// *******************SUPPRIMER REPONSE****************************
//Reponse_QuizCRUD rep_quiz = new Reponse_QuizCRUD();
////Reponse_Quiz rep_quiz2= new Reponse(11, "ghvg", "", "", 0, "", 0);
////       rep_quiz.supprimerReponse(rep_quiz2);
////// 
////     
////     
//     
//     
//     
//            
//
////        System.out.println(rep_quiz.AfficherReponse_quizs(11));
  }
//
}

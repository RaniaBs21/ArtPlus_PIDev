/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import java.util.List;

/**
 *
 * @author nour
 * @param <Question_Quiz>
 */
public interface InterfaceQuestion <Question_Quiz> {
    
    public void ajouterquestion2(Question_Quiz q );
    public void Modifier_Question(Question_Quiz q );
    public void supprimerQuestion(int id_quest); 
    public List<Question_Quiz> AfficherQuestions_quizs();
    
    
    
}

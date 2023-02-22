/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;
import artplus.entities.Question_ass;
import java.util.List;
/**
 *
 * @author akrem
 */
public interface InterfaceQuestion_ass  {
    public void ajouterquestion();
    public void ajouterquestion2 (Question_ass q);
    public void modifierquestion(Question_ass q);
    public void supprimerquestion(int id);
    public List<Question_ass> afficherquestion();
}

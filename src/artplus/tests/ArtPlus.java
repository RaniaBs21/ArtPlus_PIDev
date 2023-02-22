/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.tests;
import artplus.utils.MyConnection; 
import artplus.entities.Question_ass;
import artplus.entities.Reponse_ass;
import artplus.entities.Reclamation;
import artplus.services.Question_assCRUD;
import artplus.services.ReclamationCRUD;
import artplus.services.Reponse_assCRUD;

public class ArtPlus {
/**
 *
 * @author akrem
 */
    public static void main(String[] args) {
      MyConnection mx = MyConnection.getInstance();
      //MyConnection mx3 = MyConnection.getInstance();
      //  System.out.println(mc.hashCode()+" - "+mx3.hashCode());
      //MyConnection mx3 = MyConnection.getInstance();
      //  System.out.println(mc.hashCode()+" - "+mx3.hashCode());
       
        
      /* Question_assCRUD q = new Question_assCRUD();
        q.ajouterquestion();
       
       //Question_assCRUD q = new Question_assCRUD();
       /* Question q1 = new question("error", 1);
        q1.ajouterquestion2(q1);*/
       
       //q1.supprimerquestion(1);
         
         
         //q1.modifierquestion(q);
        
        //System.out.println(q1.afficherquestion());
        
       /* ReclamationCRUD  rec = new ReclamationCRUD();
        rec.ajouterreclamation();*/
        
       //ReclamationCRUD  rec = new ReclamationCRUD()();
      /* ReclamationCRUD r1 = new ReclamationCRUD(1,,"type produit","reclamation d'un produit");
        rec.ajouterreclamation(r1);*/
       // rec.supprimerreclamation(3);
       
       //rec.modifierreclamation(r);
       //System.out.println(r1.afficherreclamation());
       
       /* Reponse_assCRUD repa = new Reponse_assCRUD();
        repa.ajouterReponse();
       
       //Reponse_assCRUD repa = new Reponse_assCRUD();
       /* reponse ra1 = new reponse("solution",1,"description reponse");
       repa.ajouterreponse2(ra);*/
       
       //repa.supprimerreponse(1);
         
         
         //repa.modifierreponse(ra);
        
        //System.out.println(repa.affichereponse());
    }
    
}



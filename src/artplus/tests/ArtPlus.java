/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.tests;

import artplus.entities.Commentaire;
import artplus.entities.Jaime;
import artplus.entities.Post;
import artplus.services.CommentaireServices;
import artplus.services.JaimeServices;
import artplus.services.PostServices;
import artplus.utils.MyConnection;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ArtPlus {
    public static void main(String[] args) {
      MyConnection mc = MyConnection.getInstance();
      
//************* CRUD Post****************** //
        //PostServices ps = new PostServices();
        //Post p = new Post("img","img","Img");
        //ps.ajouterPost();
        //ps.ajouterPost2(p);
         /*Post p = new Post(1,"img","img","Img");
        ps.modifierPost(p);*/
        //ps.supprimerPost(2);
        //System.out.println(ps.afficherPost());
    
//************* CRUD Commentaire****************** 
    
        //CommentaireServices crd = new CommentaireServices();
        //Commentaire c = new Commentaire("rania",5);
        //crd.ajouterCommentaire();
        //crd.ajouterCommentaire2(c);
        /* Commentaire c = new Commentaire(1,"commentaire");
        crd.modifierCommentaire(c);*/
        //crd.supprimerCom(2);;
        //System.out.println(crd.afficherCommentaire());
      
   
//************* CRUD jaime ******************      
        //JaimeServices jrd = new JaimeServices();
        //Jaime j = new Jaime(5);
        //jrd.ajouterJaime();
        //jrd.ajouterJaime2(j);
         /*Jaime j = new Jaime(1,10);
        jrd.modifierJaime(j);*/
        //jrd.supprimerJaime(2);;
        //System.out.println(jrd.afficherJaime());
    }
}

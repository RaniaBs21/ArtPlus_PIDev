/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Commentaire;
import entities.Evenement;
import entities.Participation;
import entities.Sujet;
import entities.Topic;
import entities.Utilisateur_1;
import java.text.SimpleDateFormat;
import java.util.Date;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;
import utils.MyConnection;
import java.util.Scanner;
import services.CoursServices;
import services.EvenementService;
import services.GuideService;
import services.ParticipationService;
import services.ServiceTopicIMP;
import services.UtilisateurService;
/**
 *
 * @author rania
 */
public class Main {
    public static void main(String[] args) {
        Scanner rep = new Scanner(System.in);   
        int choix,boucle=1;
        MyConnection.getInstance();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        System.out.println("Today's date is: "+dateFormat.format(date));
//        //DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyy/mm/dd");
//        //System.out.println(dateTime.format(datePattern));
        ServiceSujetIMP sp = new ServiceSujetIMP();
//     Sujet sujet= new Sujet(4,"test","ce-ci est un udpate",dateFormat.format(date),0,0,1);
//        //sp.ajout(new Sujet("test","ce-ci est un test",dateFormat.format(date),0,0,1));
//        //sp.supprimer(1);
//        //sp.modifier(sujet);
////        sp.afficher().forEach(System.out::println);
//
//
    ServiceCommentaireIMP sc= new ServiceCommentaireIMP();
    
    ServiceTopicIMP st= new ServiceTopicIMP();
//  //  sc.ajout(new Commentaire("ceci est un commentaire de test",dateFormat.format(date),2,1));
////    sc.supprimer(4);
////    
////    sc.afficher().forEach(System.out::println);
//   // sc.modifier(new Commentaire(3,"ceci est un commentaire d'update",dateFormat.format(date),2,1));
//        System.out.println(sujet);
//        System.out.println("les commentaires :");
//    sc.affichercommentairebysujet(sujet).forEach(System.out::println);

 while(boucle==1){
        do
        {
        System.out.println("1-ajouter sujet");
        System.out.println("2-afficher les sujets");
        System.out.println("3-modifier sujet");
        System.out.println("4-supprimer sujet");
        System.out.println("5-ajouter commentaire");
        System.out.println("6-afficher les commentaire");
        System.out.println("7-modifier commentaire");
        System.out.println("8-supprimer commentaire");
        System.out.println("9-afficher les commentaire par sujet");
        System.out.println("10-valider un sujet par admin");
        System.out.println("11-afficher les sujets deja validé");
        System.out.println("12-ajouter topic");
        System.out.println("13-afficher les topics");
        System.out.println("14-modifier topic");
        System.out.println("15-supprimer topic");
        System.out.println("16-afficher les sujets par topic");
        System.out.println("0-Quitter");
        choix=rep.nextInt();
        }while(choix<0||choix>16);
        
        switch(choix)
        {
            case 1 :st.afficher().forEach(System.out::println);
                        Scanner topicc = new Scanner(System.in);
                    System.out.println("donner l'id du topic pour l'ajouter un sujet");
                     int idtop = topicc.nextInt();
                    Scanner sujet = new Scanner(System.in);  
                    System.out.println("donner le titre du sujet");
                     String titresujet = sujet.nextLine();
                     System.out.println("donner le contenu du sujet");
                      String contenusujet = sujet.nextLine();
                     Sujet su=new Sujet();
                     su.setContenu(contenusujet);
                     su.setTitresujet(titresujet);
                     su.setDate(dateFormat.format(date));
                     su.setIdtopic(idtop);
                      su.setIduser(1);
                        if(sp.ajout(su)){   
                            System.out.println("sujet ajouté avec succes");
                            int nbsujet =0;
                         nbsujet=st.getnbsujet(idtop);
                         System.out.println(nbsujet);
                         nbsujet++;
                         st.setnbsujet(idtop,nbsujet);
                            }
                     else
                        System.out.println("ajout echoué");

                     
                    break;
            case 2 :  sp.afficher().forEach(System.out::println);
                    break;
            case 3 :sp.afficher().forEach(System.out::println); 
                    Scanner sujetmod = new Scanner(System.in);  
                     System.out.println("donner l'id du sujet à modifier");
                     int idmod = sujetmod.nextInt();
                     Scanner sujetmodd = new Scanner(System.in);
                    System.out.println("donner le titre du sujet\n");
                     String titresujetmod = sujetmodd.nextLine();
                     System.out.println("donner le contenu du sujet");
                      String contenusujetmod = sujetmodd.nextLine();
                     Sujet sumod=new Sujet();
                     sumod.setIdsujet(idmod);
                     sumod.setContenu(contenusujetmod);
                     sumod.setTitresujet(titresujetmod);
                     sumod.setDate(dateFormat.format(date));
                    if(sp.modifier(sumod))
                        System.out.println("sujet modifié avec succes");
                     else
                        System.out.println("modification echoué");
                    break;
            case 4 : sp.afficher().forEach(System.out::println);
                    Scanner sujetsup = new Scanner(System.in);  
                    System.out.println("donner l'id du sujet à supprimer");
                     int idsup = sujetsup.nextInt();
                    if(sp.supprimer(idsup))
                        System.out.println("suppression avec succes");
                     else
                        System.out.println("suppression echoué");
                    break;
            case 5 : sp.afficher().forEach(System.out::println);
                    Scanner commentaire = new Scanner(System.in);
                    System.out.println("donner l'id du sujet pour l'ajouter un commentaire");
                     int id = commentaire.nextInt();
                     Scanner commentaire2 = new Scanner(System.in);
                    System.out.println("donner le contenu du sujet");
                      String contenucommnetaire = commentaire2.nextLine();
                     Commentaire com=new Commentaire();
                     com.setIdsujet(id);
                     com.setIduser(1);
                     com.setContenu(contenucommnetaire);
                     com.setDate(dateFormat.format(date));
                     if(sc.ajout(com))
                     {System.out.println("Commentaire ajouté avec succes");
                         int nbcom =0;
                         nbcom=sp.getnbcom(id);
                         System.out.println(nbcom);
                         nbcom++;
                         sp.setnbcom(id,nbcom);
                         }
                     else
                        System.out.println("ajout echoué");

                    break;
            case 6 : sc.afficher().forEach(System.out::println);
                    break;
            case 7 :sc.afficher().forEach(System.out::println); 
                    Scanner commentairemod = new Scanner(System.in);   
                     System.out.println("donner l'id du commentaire à modifier");
                     int idcommod = commentairemod.nextInt();
                      Scanner commentairemodcont = new Scanner(System.in);
                     System.out.println("donner le contenu du commentaire");
                     String contenucommnetairemod = commentairemodcont.nextLine();
                     Commentaire commod=new Commentaire();
                     commod.setContenu(contenucommnetairemod);
                     commod.setDate(dateFormat.format(date));
                     commod.setIdcom(idcommod);
                     if(sc.modifier(commod))
                         System.out.println("Commentaire modifier avec succes");
                     else
                        System.out.println("modification echoué");
                    break;
            case 8 : sc.afficher().forEach(System.out::println);
                    Scanner commentairesup = new Scanner(System.in);  
                    System.out.println("donner l'id du sujet à supprimer");
                     int idcom = commentairesup.nextInt();
                    if(sc.supprimer(idcom))
                        System.out.println("suppression avec succes");
                     else
                        System.out.println("suppression echoué");
                    
                    break;
            case 9 :sp.afficher().forEach(System.out::println);
                    Scanner commentairesujet = new Scanner(System.in);  
                    System.out.println("donner l'id du sujet pour voir ses commentaires");
                     int idcsujet = commentairesujet.nextInt(); 
                    sc.affichercommentairebysujet(idcsujet).forEach(System.out::println);
                    break;
            case 10 :sp.afficher().forEach(System.out::println);
                    Scanner validersuj = new Scanner(System.in);  
                    System.out.println("donner l'id du sujet pour le valider");
                     int sujetvalide = validersuj.nextInt(); 
                     if(sp.validersujet(sujetvalide))
                         System.out.println("validation avec succes");
                     else
                        System.out.println("validation echoué");
                    break;
            case 11 :sp.affichersujetvalide().forEach(System.out::println);
                    break;
             case 12 :Scanner topic = new Scanner(System.in);  
                    System.out.println("donner le titre du topic");
                     String titretopic = topic.nextLine();
                     System.out.println("donner la description du topic");
                      String desc = topic.nextLine();
                     Topic t=new Topic();
                     t.setDescription(desc);
                     t.setTitretopic(titretopic);
                     t.setDate(dateFormat.format(date));
                      t.setIduser(1);
                     if(st.ajout(t))
                         System.out.println("topic ajouté avec succes");
                     else
                        System.out.println("ajout echoué");
                    break;
             case 13 :st.afficher().forEach(System.out::println);
                    break;
             case 14 :st.afficher().forEach(System.out::println); 
                    Scanner topicmod = new Scanner(System.in);  
                     System.out.println("donner l'id du topic à modifier");
                     int idtmod = topicmod.nextInt();
                     Scanner topicmodd = new Scanner(System.in);
                    System.out.println("donner le titre du sujet\n");
                     String titretopicmod = topicmodd.nextLine();
                     System.out.println("donner le contenu du sujet");
                      String desctopicmod = topicmodd.nextLine();
                     Topic tmod=new Topic();
                     tmod.setIdtopic(idtmod);
                     tmod.setDescription(desctopicmod);
                     tmod.setTitretopic(titretopicmod);
                     tmod.setDate(dateFormat.format(date));
                    if(st.modifier(tmod))
                        System.out.println("topic modifié avec succes");
                     else
                        System.out.println("modification echoué");
                    break;
             case 15 :st.afficher().forEach(System.out::println);
                    Scanner topicsup = new Scanner(System.in);  
                    System.out.println("donner l'id du sujet à supprimer");
                     int idtsup = topicsup.nextInt();
                    if(st.supprimer(idtsup))
                        System.out.println("suppression avec succes");
                     else
                        System.out.println("suppression echoué");
                    break;
             case 16 :st.afficher().forEach(System.out::println);
                    Scanner topics = new Scanner(System.in);  
                    System.out.println("donner l'id du topic pour afficher ses sujets");
                     int idtopicsuj = topics.nextInt();
                        sp.affichersujetbytopic(idtopicsuj).forEach(System.out::println);
                    break;
            
            case 0 :System.out.println("Au revoir");
                    boucle=0;
                    break;
        }
    }
     //Evenement
        
        EvenementService evs = new EvenementService();
        GuideService gS = new GuideService();
        Evenement e = new Evenement("tessssssst",
                "Culture", 
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "C:/xampp/htdocs/artplus/ArtPlus_3A3/src/artplus/images/img1.png",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(1));
        Evenement e2 = new Evenement(4, "Visitez la musée de bardo",
                "Culture",
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tekiano.com%2F2021%2F12%2F28%2Fconcert-nouvel-an-2022-carthage-symphony-orchestra-lorchestre-symphonique-tunisien-au-rdv%2F&psig=AOvVaw2HOAzWvy337JLR8wyAC8TL&ust=1676806733434000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCKCgkIT-nv0CFQAAAAAdAAAAABAI",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(4));
        Evenement e3 = new Evenement(4, "test",
                "tesssssssssssssssst",
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "C:/Users/rahma/Documents/GitHub/ArtPlus_PIDev/src/images/img1.png",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(4));
         evs.ajouterEvenement(e);
        //evs.modifierEvenement(e2);
        //evs.supprimerEvenement(5);
        // pour afficher la liste des evenements
        //System.out.println(evs.afficherEvenements());
         
 
        ParticipationService partserv = new ParticipationService();

        UtilisateurService us =new UtilisateurService();

        Participation part;
        part = new Participation(us.findOneById(1), evs.findOneById(4), java.sql.Timestamp.from(java.time.Instant.now()));
        partserv.ajouterParticipation(part);
        Utilisateur_1 user = new Utilisateur_1(1, "mohamed", "ali", "****", "mohamed.ali@x.tn", "ariana", "2222222");
        Participation part2 = new Participation(3,
                us.findOneById(1),
                evs.findOneById(4),
                java.sql.Timestamp.from(java.time.Instant.now()));
        partserv.modifierParticipation(part2);
        partserv.supprimerParticipation(3);
        System.out.println(partserv.afficherParticipation());

       
 /*
        EvenementService evs = new EvenementService();
        GuideService gS = new GuideService();
        UtilisateurService uS = new UtilisateurService();
        Utilisateur_1 user = new Utilisateur_1(1, "mohamed", "ali", "****", "mohamed.ali@x.tn", "ariana", "2222222");

        Evenement e2 = new Evenement(9, "Visitez la musée de bardo",
                "Culture",
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tekiano.com%2F2021%2F12%2F28%2Fconcert-nouvel-an-2022-carthage-symphony-orchestra-lorchestre-symphonique-tunisien-au-rdv%2F&psig=AOvVaw2HOAzWvy337JLR8wyAC8TL&ust=1676806733434000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCKCgkIT-nv0CFQAAAAAdAAAAABAI",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(4));
        Evenement e3 = new Evenement(4, "test",
                "test",
                "teeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeest",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tekiano.com%2F2021%2F12%2F28%2Fconcert-nouvel-an-2022-carthage-symphony-orchestra-lorchestre-symphonique-tunisien-au-rdv%2F&psig=AOvVaw2HOAzWvy337JLR8wyAC8TL&ust=1676806733434000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCKCgkIT-nv0CFQAAAAAdAAAAABAI",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(4));
        Participation p = new Participation(user, e2, java.sql.Timestamp.from(java.time.Instant.now()));
        Participation p2 = new Participation(4,user, e3, java.sql.Timestamp.from(java.time.Instant.now()));
        ParticipationService partserv = new ParticipationService();

        
        //partserv.ajouterParticipation(p);
        //partserv.modifierParticipation(p2);
        //partserv.supprimerParticipation(3);
        System.out.println(partserv.afficherParticipation());


    }*/
    }
    

}


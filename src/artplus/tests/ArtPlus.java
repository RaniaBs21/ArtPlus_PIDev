/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.tests;

import artplus.entities.Evenement;
import artplus.entities.Participation;
import artplus.services.EvenementService;
import artplus.services.GuideService;
import artplus.services.ParticipationService;
import artplus.services.UtilisateurService;
import artplus.utils.MyConnection;

/**
 *
 * @author rahma
 */
public class ArtPlus {

    public static void main(String[] args) {
        MyConnection mc = MyConnection.getInstance();

        EvenementService evs = new EvenementService();
        GuideService gS = new GuideService();
        Evenement e = new Evenement("tessssssst",
                "Culture",
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tekiano.com%2F2021%2F12%2F28%2Fconcert-nouvel-an-2022-carthage-symphony-orchestra-lorchestre-symphonique-tunisien-au-rdv%2F&psig=AOvVaw2HOAzWvy337JLR8wyAC8TL&ust=1676806733434000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCKCgkIT-nv0CFQAAAAAdAAAAABAI",
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
                "test",
                "Vous puvez visiter la musée de bardo, avec notre guide professionnel, le 13-03-2023 à 10:00",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.tekiano.com%2F2021%2F12%2F28%2Fconcert-nouvel-an-2022-carthage-symphony-orchestra-lorchestre-symphonique-tunisien-au-rdv%2F&psig=AOvVaw2HOAzWvy337JLR8wyAC8TL&ust=1676806733434000&source=images&cd=vfe&ved=0CBAQjRxqFwoTCKCgkIT-nv0CFQAAAAAdAAAAABAI",
                "La musée de bardo",
                java.sql.Timestamp.valueOf("2023-03-13 10:00:00"),
                25, gS.findOneById(4));
        /*evs.ajouterEvenement(e);
        evs.modifierEvenement(e2);
        evs.supprimerEvenement(5);
        //*** pour afficher la liste des evenements
        System.out.println(evs.afficherEvenements());*/

        ParticipationService partserv = new ParticipationService();

        UtilisateurService us = new UtilisateurService();

        Participation part;
        part = new Participation(us.findOneById(1), evs.findOneById(4), java.sql.Timestamp.from(java.time.Instant.now()));
        partserv.ajouterParticipation(part);
        Participation part2 = new Participation(3,us.findOneById(1), evs.findOneById(4), java.sql.Timestamp.from(java.time.Instant.now()));
        partserv.modifierParticipation(part2);
        partserv.supprimerParticipation(3);
    }

}

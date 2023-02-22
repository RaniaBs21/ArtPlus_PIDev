/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.services;

import artplus.entities.Post;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface InterfacePostServices {
     public void ajouterPost();
    public void ajouterPost2(Post p);
    public void modifierPost(Post p);
    public void supprimerPost(int Id_Post);
    public List<Post> afficherPost();
    
}

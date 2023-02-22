/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artplus.entities;

import java.sql.Time;
import java.util.Date;


/**
 *
 * @author DELL
 */
public class Post {
    private int Id_Post;
    private String Description_Post;
    private Date Date_Post;
    private Time Heure_Post;
    private String Img_Post;

    public Post(int Id_Post, String Description_Post, Date Date_Post, Time Heure_Post, String Img_Post) {
        this.Id_Post = Id_Post;
        this.Description_Post = Description_Post;
        this.Date_Post = Date_Post;
        this.Heure_Post = Heure_Post;
        this.Img_Post = Img_Post;
    }

    public Post( String Description_Post, Date Date_Post, Time Heure_Post, String Img_Post) {
        this.Description_Post = Description_Post;
        this.Date_Post = Date_Post;
        this.Heure_Post = Heure_Post;
        this.Img_Post = Img_Post;
    }

    public Post() {
    }

    public Post(String Description_Post, String Img_Post) {
        this.Description_Post = Description_Post;
        this.Img_Post = Img_Post;
    }

    public Post(int Id_Post, String Description_Post, String Img_Post) {
        this.Id_Post = Id_Post;
        this.Description_Post = Description_Post;
        this.Img_Post = Img_Post;
    }

    public int getId_Post() {
        return Id_Post;
    }

    public void setId_Post(int Id_Post) {
        this.Id_Post = Id_Post;
    }


    public String getDescription_Post() {
        return Description_Post;
    }

    public void setDescription_Post(String Description_Post) {
        this.Description_Post = Description_Post;
    }

    public Date getDate_Post() {
        return Date_Post;
    }

    public void setDate_Post(Date Date_Post) {
        this.Date_Post = Date_Post;
    }

    public Time getHeure_Post() {
        return Heure_Post;
    }

    public void setHeure_Post(Time Heure_Post) {
        this.Heure_Post = Heure_Post;
    }

    public String getImg_Post() {
        return Img_Post;
    }

    public void setImg_Post(String Img_Post) {
        this.Img_Post = Img_Post;
    }

    @Override
    public String toString() {
        return "Post{" + "Id_Post=" + Id_Post + ", Description_Post=" + Description_Post + ", Date_Post=" + Date_Post + ", Heure_Post=" + Heure_Post + ", Img_Post=" + Img_Post + '}';
    }
    
    
}

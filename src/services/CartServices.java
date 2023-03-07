/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services
        ;

import entities.Cart;
import entities.Produit;
import services.ProduitCrud;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.activation.DataSource;

/**
 *
 * @author Lenovo
 */



public class CartServices {
    Connection cnx;
    private List<Produit> cart;

    public CartServices() {
        this.cart = new ArrayList<>();
        cnx = MyConnection.getInstance().getConnection();
    }

   public void addtocart(int id) {
       ProduitCrud pcd = new ProduitCrud();
       
    Produit p = pcd.rechercherProduitbyid(id);
    if (p != null) {
        this.cart.add(p);
        System.out.println("Produit ajouté au panier avec succès !");
    } else {
        System.out.println("Produit non trouvé !");
    }
}

    public void removefromcart(Produit p) {
        
        Iterator<Produit> it = this.cart.iterator();
        while (it.hasNext()) {
            Produit prod = it.next();
            if (prod.getId_Prod() == p.getId_Prod()) {
                it.remove();
                System.out.println("Produit retiré du panier avec succès !");
                break;
            }
        }
    }

    public void emptycart() {
        this.cart.clear();
        System.out.println("Le panier a été vidé avec succès !");
    }

 public int totalcart() {
    int total = 0;
    for (Produit p : this.cart) {
        System.out.println("Produit: " + p.getType_Prod() + ", Prix: " + p.getPrix_Prod());
        total += p.getPrix_Prod();
    }
    System.out.println("Total: " + total);
    return total;
}
    public void afficherCart() {
    System.out.println("Votre panier contient:");
    for (Produit p : cart) {
        System.out.println(p.toString());
    }
}
    private String convertProductsListToString(List<Produit> products) {
    StringBuilder sb = new StringBuilder();
    for (Produit p : products) {
        sb.append(p.getId_Prod()).append(",");
    }
   
    if (sb.length() > 0) {
        sb.setLength(sb.length() - 1);
    }
    return sb.toString();
}
  public void saveCartToDatabase(Cart cart) {
    try (
        PreparedStatement stmt = cnx.prepareStatement(
            "INSERT INTO cart (id_cart, Id_Prod) VALUES (?, ?)"
        )
    ) {
        stmt.setInt(1, cart.getIdCart());
        String Id_Prod = null;
        stmt.setString(2, Id_Prod);
        StringBuilder sb = new StringBuilder();
        for (Produit p : cart.getProducts()) {
            sb.append(p.getId_Prod()).append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); 
        }
        stmt.setString(2, sb.toString());
        stmt.executeUpdate();
        System.out.println("Cart saved to database successfully!");
    } catch (SQLException e) {
        System.out.println("Error saving cart to database: " + e.getMessage());
    }
}



  
    
}

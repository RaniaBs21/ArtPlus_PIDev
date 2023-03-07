package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int Id_Cart;
    private List<Produit> products;

    public Cart(int Id_Cart, List<Produit> products) {
        this.Id_Cart = Id_Cart;
        this.products = products;
    }

    public Cart() {
        this(0, new ArrayList<>());
    }

    public int getIdCart() {
        return Id_Cart;
    }

    public void setIdCart(int Id_Cart) {
        this.Id_Cart = Id_Cart;
    }

    public List<Produit> getProducts() {
        return products;
    }

    public void setProducts(List<Produit> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" + "Id_Cart=" + Id_Cart + ", products=" + products + '}';
    }
}
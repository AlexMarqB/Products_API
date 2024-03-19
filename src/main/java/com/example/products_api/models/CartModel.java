package com.example.products_api.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_cart")
public class CartModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCart;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ItemModel> items;

    private String name;

    public UUID getIdCart() {
        return idCart;
    }

    public void setIdCart(UUID idCart) {
        this.idCart = idCart;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

}
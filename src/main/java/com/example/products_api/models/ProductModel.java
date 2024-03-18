package com.example.products_api.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

// mapeamento informando que é uma entidade
@Entity
@Table(name = "tb_products")        //Marcação dizendo ser serial
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    // definimos que o proximo atributo sera o identificador
    @Id
    // definimos como ele será gerado
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProduct;
    private String name;
    private BigDecimal value;

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

package com.example.products_api.respositories;

import com.example.products_api.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// interface == classe abstrata
// definimos para o spring que essa classe será gerenciada por ele
// JPARepository == cria automaticamente todos os metódos necessario para manipular a tabela
@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}

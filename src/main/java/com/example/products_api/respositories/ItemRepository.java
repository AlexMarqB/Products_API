package com.example.products_api.respositories;

import com.example.products_api.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, UUID> {
    List<ItemModel> findAllByCartId(UUID cartId);
    ItemModel findByIdAndCartId(UUID id, UUID cartId);
}

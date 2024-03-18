package com.example.products_api.dtos;

import java.util.UUID;

public record ItemRecordDto(UUID productId, UUID cartId) {
}

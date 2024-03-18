package com.example.products_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//record = tipo especial do java, imutaveis, getters, atributos private por padrão
//os parametros são os atributos que vamos receber
public record ProductRecordDto(@NotBlank String name,@NotNull BigDecimal value) {

}

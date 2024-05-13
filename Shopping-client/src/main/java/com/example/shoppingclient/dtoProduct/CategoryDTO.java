package com.example.shoppingclient.dtoProduct;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

    private long id;
    @NotBlank(message = "Nome não pode ser em branco")
    private final String nome;

    public CategoryDTO(final String nome) {
        this.nome = nome;
    }

    public CategoryDTO(final long id, final String nome) {
        this.id = id;
        this.nome = nome;
    }


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
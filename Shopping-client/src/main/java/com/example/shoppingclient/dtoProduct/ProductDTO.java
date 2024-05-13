package com.example.shoppingclient.dtoProduct;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank(message = "Nome não pode ser em branco")
    private final String nome;
    @NotNull(message = "Preço deve ser informado")
    private final Float preco;
    @NotBlank(message = "Descrição deve ser informada")
    private final String descricao;
    @NotBlank(message = "Identificação do produto deve ser informado")
    private final String productIdentifier;
    private final CategoryDTO categoryDTO;

    public ProductDTO (final String nome, final Float preco, final String descricao,
                       final String productIdentifier, final CategoryDTO categoryDTO) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.productIdentifier = productIdentifier;
        this.categoryDTO = categoryDTO;
    }



    public String getNome() {
        return nome;
    }

    public Float getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }



}

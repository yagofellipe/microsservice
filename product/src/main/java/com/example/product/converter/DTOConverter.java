package com.example.product.converter;


import com.example.product.model.Product;
import com.example.product.model.Category;
import com.example.shoppingclient.dtoProduct.ProductDTO;
import com.example.shoppingclient.dtoProduct.CategoryDTO;

public class DTOConverter {

    public static CategoryDTO convert(Category category) {
        return new CategoryDTO(category.getId(), category.getNome());
    }

    public static ProductDTO convert(Product product) {
        CategoryDTO categoryDTO = product.getCategory() != null ? DTOConverter.convert(product.getCategory()) : null;
        return new ProductDTO(product.getNome(), product.getPreco(), product.getDescricao(), product.getProductIdentifier(), categoryDTO);
    }

}

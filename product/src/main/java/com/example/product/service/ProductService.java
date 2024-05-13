package com.example.product.service;

import com.example.product.converter.DTOConverter;
import com.example.product.model.Product;
import com.example.product.repository.CategoryRepository;
import com.example.product.repository.ProductRepository;
import com.example.shoppingclient.dtoProduct.ProductDTO;
import com.example.shoppingclient.errorDTO.CategoryNotFoundException;
import com.example.shoppingclient.errorDTO.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository catRepository;

    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(final long categoryId) {
        return repository.getProductByCategory(categoryId).stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(final String productIdentifier) {
        return repository.findByProductIdentifierIgnoreCase(productIdentifier)
                .map(DTOConverter::convert)
                .orElseThrow(() -> new ProductNotFoundException("ProductIdentifier not found"));
    }

    public ProductDTO save (ProductDTO dto) {

        catRepository.findById(dto.getCategoryDTO().getId())
                .orElseThrow(() -> new CategoryNotFoundException("Catetory not found"));

        Product product = repository.save(Product.convert(dto));
        return DTOConverter.convert(product);
    }


    public void delete(final long productId) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Id not found"));
        repository.delete(product);
    }

}

package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p "
            + "from product p "
            + "join p.category c "
            + "where c.id = :categoryId")
    List<Product> getProductByCategory(@Param("categoryId") long categoryId);

    public Optional<Product> findByProductIdentifierIgnoreCase(final String productIdentifier);

}

package com.example.ecommerce.dao;

import com.example.ecommerce.dto.ProductQueryParameter;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductDao {
    Integer countProduct(ProductQueryParameter productQueryParameter);
    List<Product> getProducts(ProductQueryParameter productQueryParameter);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void updateStock(Integer productId,Integer stock);
    void deleteProductById(Integer productId);
}

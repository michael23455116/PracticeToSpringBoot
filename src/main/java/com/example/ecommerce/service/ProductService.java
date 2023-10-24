package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductQueryParameter;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(ProductQueryParameter productQueryParameter);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);
}

package com.example.ecommerce.dao;

import com.example.ecommerce.constant.ProductCategory;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductCategory category,String search);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId,ProductRequest productRequest);
    void deleteProductById(Integer productId);
}

package com.example.ecommerce.controller;

import com.example.ecommerce.constant.ProductCategory;
import com.example.ecommerce.dto.ProductQueryParameter;
import com.example.ecommerce.dto.ProductRequest;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>>getProducts(
            //查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序
            @RequestParam(defaultValue = "created_date") String orderby,
            @RequestParam(defaultValue = "desc") String sort
            ){

        ProductQueryParameter productQueryParameter = new ProductQueryParameter();
        productQueryParameter.setCategory(category);
        productQueryParameter.setSearch(search);
        productQueryParameter.setOrderby(orderby);
        productQueryParameter.setSort(sort);

        List<Product> productList = productService.getProducts(productQueryParameter);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);

        if (product!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product>createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product>roupdateProduct(@PathVariable Integer productId,
                                                  @RequestBody @Valid ProductRequest productRequest){
        //檢查商品是否存在
        Product product = productService.getProductById(productId);
        if(product==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //修改商品數據
        productService.updateProduct(productId,productRequest);

        Product updateProduct = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<?>deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
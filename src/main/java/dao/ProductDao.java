package dao;

import model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);
}

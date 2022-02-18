package service;

import model.Product;

import java.util.ArrayList;

public interface IProductService {
    ArrayList<Product> getAll();

    void createProduct(Product product);

    void updateProduct(Product product, int index);

    void deleteProduct(Product product);

    Product getProduct(int id);
}

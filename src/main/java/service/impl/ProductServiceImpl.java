package service.impl;

import model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.IProductService;

import java.util.ArrayList;

@Service
@Qualifier(value = "productService")
public class ProductServiceImpl implements IProductService {
    private static final ArrayList<Product> products;

    static {
        products = new ArrayList<>();
        products.add(new Product(1, "A", 10000, "a"));
        products.add(new Product(2, "B", 20000, "b"));
        products.add(new Product(3, "C", 30000, "c"));
    }

    @Override
    public ArrayList<Product> getAll() {
        return products;
    }

    @Override
    public void createProduct(Product product) {
        products.add(product);
    }

    @Override
    public void updateProduct(Product product, int index) {
        products.set(index, product);
    }

    @Override
    public void deleteProduct(Product product) {
        products.remove(product);
    }

    @Override
    public Product getProduct(int id) {
        for(Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}

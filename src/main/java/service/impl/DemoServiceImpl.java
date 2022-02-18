package service.impl;

import model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.IProductService;

import java.util.ArrayList;

@Service
@Qualifier(value = "demoService")
public class DemoServiceImpl implements IProductService {
    @Override
    public ArrayList<Product> getAll() {
        return null;
    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product, int index) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public Product getProduct(int id) {
        return null;
    }
}

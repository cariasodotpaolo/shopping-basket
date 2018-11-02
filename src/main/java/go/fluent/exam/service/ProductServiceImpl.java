package go.fluent.exam.service;

import go.fluent.exam.entity.ProductEntity;
import go.fluent.exam.model.Product;
import go.fluent.exam.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {


    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {

        ProductEntity entity = new ProductEntity();
        //TODO: map fields here
        return new Product(productRepository.save(entity));
    }

    public Product get(Long productId) {

        return new Product(productRepository.findOne(productId));
    }
}

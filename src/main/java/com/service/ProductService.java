package com.service;

import com.model.Category;
import com.model.Product;
import com.repository.CategoryRepository;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void saveProduct(Product product) {
        Category category = categoryRepository.findByCategoryName(product.getCategory().getCategoryName());
        product.setCategory(category);
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.delete(id);
    }

    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }
}

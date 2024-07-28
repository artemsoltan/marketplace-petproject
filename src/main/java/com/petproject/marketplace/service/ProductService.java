package com.petproject.marketplace.service;

import com.petproject.marketplace.model.dto.CreateProductDTO;
import com.petproject.marketplace.exception.NotAccessException;
import com.petproject.marketplace.exception.ProductDoesNotExistsException;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.model.entity.Product;
import com.petproject.marketplace.model.entity.User;
import com.petproject.marketplace.repository.ProductRepository;
import com.petproject.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public void save(CreateProductDTO createProductDTO, String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new UserDoesNotExistsException();
        }

        Product product = new Product(
                createProductDTO.getTitle(),
                createProductDTO.getDescription(),
                createProductDTO.getPrice(),
                user
        );

        productRepository.save(product);
    }

    public Product getProduct(int id, String username) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductDoesNotExistsException();
        } else if (!product.getUser().getUsername().equals(username)) {
            throw new NotAccessException();
        }

        return product;
    }

    public void deleteProduct(int id, String username) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductDoesNotExistsException();
        } else if (!product.getUser().getUsername().equals(username)) {
            throw new NotAccessException();
        }

        productRepository.delete(product);
    }

    public void updateProduct(int id, CreateProductDTO createProductDTO, String username) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            throw new ProductDoesNotExistsException();
        } else if (!product.getUser().getUsername().equals(username)) {
            throw new NotAccessException();
        }

        product.setTitle(createProductDTO.getTitle());
        product.setDescription(createProductDTO.getDescription());
        product.setPrice(createProductDTO.getPrice());

        productRepository.save(product);
    }
}
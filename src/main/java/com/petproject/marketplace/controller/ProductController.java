package com.petproject.marketplace.controller;

import com.petproject.marketplace.config.jwt.JwtUtil;
import com.petproject.marketplace.model.dto.CreateProductDTO;
import com.petproject.marketplace.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final JwtUtil jwtUtil;

    @Autowired
    public ProductController(ProductService productService, JwtUtil jwtUtil) {
        this.productService = productService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductDTO createProductDTO,
                                           @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        productService.save(createProductDTO, jwtUtil.extractUsername(token));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id,
                                        @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        return ResponseEntity.ok(productService.getProduct(id, jwtUtil.extractUsername(token)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id,
                                           @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        productService.deleteProduct(id, jwtUtil.extractUsername(token));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id,
                                           @RequestBody CreateProductDTO createProductDTO,
                                           @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        productService.updateProduct(id, createProductDTO, jwtUtil.extractUsername(token));
        return ResponseEntity.ok().build();
    }
}
package com.projetofinal5.ProjetoFinal5.controller;

import com.projetofinal5.ProjetoFinal5.entity.Product;
import com.projetofinal5.ProjetoFinal5.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value="/saveOrUpdate")
    public ResponseEntity<Void> saveOrUpdateProduct(@RequestBody Product product) {
        productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable (value = "productId", required = true) String productId) {
        productService.deleteProduct(productService.getProduct(Integer.parseInt(productId)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping(value="/getOne")
    public ResponseEntity<Product> getOneProduct(@RequestParam("productId") String productId) {
        Product product = productService.getProduct(Integer.parseInt(productId));
        return ResponseEntity.ok(product);
    }

}

package com.projetofinal5.ProjetoFinal5.service;

import com.projetofinal5.ProjetoFinal5.dao.ProductDao;
import com.projetofinal5.ProjetoFinal5.entity.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class ProductService {


    private final ProductDao productDao;

    public void saveOrUpdateProduct(Product product) {
        log.info("Inserindo produto: {}", product);
        productDao.save(product);
    }

    public List<Product> getAllProducts() {
    log.info("Buscando todos os produtos");
        return productDao.findAll();
    }

    public Product getProduct(int ProductId) {
        log.info("Buscando produto por id: {}", ProductId);
        return productDao.findById(ProductId).orElse(null);
    }

    public void deleteProduct(Product product) {
        log.info("Deletando o produto: {}", product);
        productDao.delete(product);
    }
}

package com.projetofinal5.ProjetoFinal5.dao;

import com.projetofinal5.ProjetoFinal5.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao  extends JpaRepository<Product, Integer> {

}

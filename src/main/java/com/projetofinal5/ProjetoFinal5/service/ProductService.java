package com.projetofinal5.ProjetoFinal5.service;

import com.projetofinal5.ProjetoFinal5.dto.ProductDTO;
import com.projetofinal5.ProjetoFinal5.dto.ProductListDTO;
import com.projetofinal5.ProjetoFinal5.entity.ProductEntity;
import com.projetofinal5.ProjetoFinal5.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProductRepository produtoRepository;

    @Autowired
    private ProductMapper produtoMapper;
    @Autowired
    private ProductRepository productRepository;


    public List<ProductDTO> criarProduto(ProductListDTO produtoListDTO) {
        List<ProductEntity> produtoEntities = produtoListDTO.getProdutoList().stream()
                .map(produtoMapper::toEntity)
                .collect(Collectors.toList());

        List<ProductEntity> produtosSalvos = productRepository.saveAll(produtoEntities);

        return produtosSalvos.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO buscarProdutoPorId(Long id) {
        ProductEntity produtoEntity = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Produto com id" + id + "não encontrado"));
        return produtoMapper.toDTO(produtoEntity);
    }

    public List<ProductDTO> listarTodosProdutos() {
        List<ProductEntity> produtoEntities = produtoRepository.findAll();
        return produtoEntities.stream().map(produtoMapper::toDTO).collect(Collectors.toList());
    }

    public ProductDTO atualizarProduto(Long id, ProductDTO produtoDTO) {
        Optional<ProductEntity> produtoEntity = produtoRepository.findById(id);
        if (produtoEntity.isPresent()) {
            ProductEntity produtoExistente = produtoEntity.get();
            produtoMapper.atualizarProduto(produtoDTO, produtoExistente);
            ProductEntity produtoSalvo = produtoRepository.save(produtoExistente);

            return produtoMapper.toDTO(produtoSalvo);
        } else {
            throw new EntityNotFoundException("Produto não encontrado com id " + id);
        }
    }


    public void deletarProduto(Long id) {
        ProductEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id " + id));

        produtoRepository.delete(produtoExistente);
    }


}
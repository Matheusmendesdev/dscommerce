package com.matheusmendesdev.Dscommerce.services;

import com.matheusmendesdev.Dscommerce.dto.ProductDTO;
import com.matheusmendesdev.Dscommerce.entities.Product;
import com.matheusmendesdev.Dscommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductDTO findById(Long id){
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}

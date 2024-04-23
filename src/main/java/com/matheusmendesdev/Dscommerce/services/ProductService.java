package com.matheusmendesdev.Dscommerce.services;

import com.matheusmendesdev.Dscommerce.dto.ProductDTO;
import com.matheusmendesdev.Dscommerce.entities.Product;
import com.matheusmendesdev.Dscommerce.repositories.ProductRepository;
import com.matheusmendesdev.Dscommerce.services.exceptions.DataBaseException;
import com.matheusmendesdev.Dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable){
        Page<Product> product = repository.searchByName(name, pageable);
        return product.map(x -> new ProductDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
        Product entity = repository.getReferenceById(id);
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ProductDTO(entity);
        }
        catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Resource Not Found!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Resouce Not Found!");
        }
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
            throw new DataBaseException("Fail reference integration");
        }
    }

    private void copyDtoToEntity(Product entity, ProductDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
    }
}

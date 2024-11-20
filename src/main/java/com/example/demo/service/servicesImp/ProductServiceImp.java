package com.example.demo.service.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        
        return productRepo.save(product);
    }
    @Override
    public Product editProduct(Product product) {
        boolean exist = productRepo.existsById(product.getId());
        if(exist){

            return productRepo.save(product);
        }
        return null;
    }
    
    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
       
    }
    @Override
    public Page<Product> getRequestFilters(int page, int limit, String productName, Direction sortType) {
        
        Page<Product> productPage = null;
        
        if(productName==null &&sortType==null){
            productPage = getProductsList(page,limit);
        }
        if(productName!=null&&sortType==null){
            productPage = findProductsByName(page, limit, productName);
        }
        if(productName==null&&sortType!=null){
            productPage = getProductOrderByPrice(page, limit, sortType);
        }
        if(productName!=null&&sortType!=null){
            productPage = findProductsByNameAndOrderByPrice(page, limit, productName, sortType);
        }
        return productPage;
    }

    private Page<Product> getProductsList(int page, int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return productRepo.findAll(pageable);
    }

    private Page<Product> findProductsByName(int page, int limit, String productName) {
        Pageable pageable = PageRequest.of(page, limit);
        return productRepo.findByNameContainingIgnoreCase(productName, pageable);
    }

    private Page<Product> getProductOrderByPrice(int page, int limit, Sort.Direction sortType){
        Sort sort = Sort.by(sortType, "price");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepo.findAll(pageable);
    }
    
    private Page<Product> findProductsByNameAndOrderByPrice(int page, int limit, String productName, Sort.Direction sortType){

        Sort sort = Sort.by(sortType, "price");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return productRepo.findByNameContainingIgnoreCase(productName, pageable);
    }
}

package com.tanphi.laptopshop.repository;

import com.tanphi.laptopshop.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
	Product findProductByProductIdAndIsdeleted(Integer productId,Integer status);
	Product findProductByProductId(Integer productId);
    List<Product> findProductByIsdeleted(Integer status);
    Page<Product> findProductByIsdeleted(Integer status, Pageable page);
    Product findProductByProductIdAndIsdeleted(int id,Integer status);
    Page<Product> findByIsdeleted(Integer status,Pageable pageable);
    @Query("Select p from Product p where p.productName like %:title% and p.isdeleted =:status")
    Page<Product> findByProductNameContainingAndIsdeleted(String title,Integer status,Pageable pageable);
//    @Query("Select p from Product p where p.productName like %:title%")
//    Page<Product> findByProductNameContaining(String title,Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:title% ORDER BY p.productId DESC")
    Page<Product> findByProductNameContaining(String title, Pageable pageable);
    Product findByProductNameAndIsdeleted(String productName,Integer status);
    Product findByProductNameAndProductIdNotAndIsdeleted(String productName,Integer id,Integer status);
    Integer countByIsdeleted(Integer status);
    
    List<Product> findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetweenAndProducerContaining(String cpu, String graphicscard, String ram, String screensize, String storagecapacity, Integer fromPriceRange, Integer toPriceRange, String producer);

    @Query("SELECT p FROM Product p ORDER BY p.productId DESC")
    Page<Product> findProductsOrderByProductIdDesc(Pageable pageable);
//    List<Product> findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetween(String cpu, String graphicscard, String ram, String screensize, String storagecapacity, Integer fromPriceRange, Integer toPriceRange);
  }    


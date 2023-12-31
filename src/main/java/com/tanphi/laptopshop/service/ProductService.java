package com.tanphi.laptopshop.service;


import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.request.product.ProductRequest;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    List<Product> GetAllProduct(Integer isDeleteStatus);

	Page<Product> getListProductCustomer(Pageable pageable);

	Product getProductById(int id);

	Page<Product> getListProductCustomerByKeyword(Pageable pageable, String keyword);

	void AddNewProduct(ProductRequest request);

	void UpdateProduct(ProductRequest request);

	void DeleteProduct(Integer productId);

	void UpdateStatusProduct(ProductRequest request);

	Page<Product> getListProductAdmin(Pageable pageable);

	Product getProductByIdAdmin(int id);

	Page<Product> getListProductAdminByKeyword(Pageable pageable, String keyword);
	
    List<Product> findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetweenAndProducerContaining(String cpu, String graphicscard, String ram, String screenSize, String storageCapacity, Integer fromPriceRange, Integer toPriceRange,String producer);

	List<Product> getFilteredProducts(String cpu, String graphicscard, String ram, String screensize,String storagecapacity, Integer fromPriceRange, Integer toPriceRange,String producer);

	Product findProductById(Integer id);

}

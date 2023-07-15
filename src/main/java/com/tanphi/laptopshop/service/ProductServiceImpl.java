package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.Supplier;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.ProductRepo;
import com.tanphi.laptopshop.repository.SupplierRepo;
import com.tanphi.laptopshop.request.product.ProductRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Override
    public List<Product> GetAllProduct(Integer isDeleteStatus) {
        return productRepo.findProductByIsdeleted(isDeleteStatus);
    }
	@Override
	public Page<Product> getListProductCustomer(Pageable pageable) {
		// TODO Auto-generated method stub
		//return productRepo.findProductByIsdeleted(IsDeleteStatus.NO.getCode(), pageable);
		//return productRepo.findAll(pageable);
        return productRepo.findProductsOrderByProductIdDesc(pageable);

	}
	
	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		//return productRepo.findProductByProductIdAndIsdeleted(id, IsDeleteStatus.NO.getCode());
		return productRepo.findProductByProductId(id);


	}
	@Override
	public Page<Product> getListProductCustomerByKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return productRepo.findByProductNameContainingAndIsdeleted(keyword, IsDeleteStatus.NO.getCode(), pageable);
	}
	@Override
	public void AddNewProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		Product dbProduct=productRepo.findByProductNameAndIsdeleted(request.getProductName(), IsDeleteStatus.NO.getCode());
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierIdAndIsdeleted(request.getSupplierId(), IsDeleteStatus.NO.getCode());
		if(dbProduct!=null)
		{
			throw new BadRequestException("Sản phẫm đã tồn tại, vui lòng thay đổi tên sản phẫm");
		}
		if(dbSupplier==null)
		{
			throw new BadRequestException("Nhà cung cấp không tồn tại, vui lòng chọn nhà cung cấp khác");
		}
		Product newProduct=new Product();
	    newProduct.setProductId(request.getProductId());//code them 1507
		newProduct.setDescription(request.getDescription());
		newProduct.setDiscount(request.getDiscount());
		newProduct.setImage(request.getImage());
		newProduct.setIsdeleted(IsDeleteStatus.NO.getCode());
		newProduct.setProductName(request.getProductName());
		newProduct.setQuantity(request.getQuantity());
		newProduct.setSupplier(dbSupplier);
		newProduct.setUnitprice(request.getUnitprice());
		newProduct.setAudiotechnology(request.getAudiotechnology());
		newProduct.setBattery(request.getBattery());
		newProduct.setCache(request.getCache());
		newProduct.setCasingmaterial(request.getCasingmaterial());
		newProduct.setCores(request.getCores());
		newProduct.setCpu(request.getCpu());
		newProduct.setCpuspeed(request.getCpuspeed());
		newProduct.setUnitprice(request.getUnitprice());
		newProduct.setThreads(request.getThreads());
		newProduct.setStoragetype(request.getStoragetype());
		newProduct.setSoldquantity(request.getSoldquantity());
		newProduct.setScreenrefreshrate(request.getScreenrefreshrate());
		newProduct.setScreen_size(request.getScreensize());
		newProduct.setScreen_resolution(request.getScreenresolution());
		newProduct.setReleasedate(request.getReleasedate());
		newProduct.setRambusspeed(request.getRambusspeed());
		newProduct.setRam_type(request.getRamtype());
		newProduct.setRam(request.getRam());
		newProduct.setStoragecapacity(request.getStoragecapacity());
		newProduct.setOperatingsystem(request.getOperatingsystem());
		newProduct.setWeight(request.getWeight());
		newProduct.setGraphicscard(request.getGraphicscard());
		newProduct.setProducer(request.getProducer());
		productRepo.save(newProduct);
	}
	@Override
	public void UpdateProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		Product dbProductById=productRepo.findProductByProductIdAndIsdeleted(request.getProductId(), IsDeleteStatus.NO.getCode());
		Product dbProductByName=productRepo.findByProductNameAndProductIdNotAndIsdeleted(request.getProductName(),request.getProductId(), IsDeleteStatus.NO.getCode());
		Supplier dbSupplier=supplierRepo.findSupplierBySupplierIdAndIsdeleted(request.getSupplierId(), IsDeleteStatus.NO.getCode());
		if(dbProductById==null)
		{
			throw new BadRequestException("Sản phẫm không tồn tại");
		}
		else if(dbProductByName!=null)
		{
			throw new BadRequestException("Tên sản phẫm bị trùng");
		}
		if(dbSupplier==null)
		{
			throw new BadRequestException("Nhà cung cấp không tồn tại, vui lòng chọn nhà cung cấp khác");
		}
//		dbProductById.setDescription(request.getDescription());
//		dbProductById.setDiscount(request.getDiscount());
//		dbProductById.setImage(request.getImage());
//		dbProductById.setProductName(request.getProductName());
//		dbProductById.setQuantity(request.getQuantity());
		dbProductById.setSupplier(dbSupplier);
		dbProductById.setDescription(request.getDescription());
		dbProductById.setDiscount(request.getDiscount());
		dbProductById.setImage(request.getImage());
		dbProductById.setIsdeleted(IsDeleteStatus.NO.getCode());
		dbProductById.setProductName(request.getProductName());
		dbProductById.setQuantity(request.getQuantity());
		dbProductById.setSupplier(dbSupplier);
		dbProductById.setUnitprice(request.getUnitprice());
		dbProductById.setAudiotechnology(request.getAudiotechnology());
		dbProductById.setBattery(request.getBattery());
		dbProductById.setCache(request.getCache());
		dbProductById.setCasingmaterial(request.getCasingmaterial());
		dbProductById.setCores(request.getCores());
		dbProductById.setCpu(request.getCpu());
		dbProductById.setCpuspeed(request.getCpuspeed());
		dbProductById.setUnitprice(request.getUnitprice());
		dbProductById.setThreads(request.getThreads());
		dbProductById.setStoragetype(request.getStoragetype());
		dbProductById.setSoldquantity(request.getSoldquantity());
		dbProductById.setScreenrefreshrate(request.getScreenrefreshrate());
		dbProductById.setScreen_size(request.getScreensize());
		dbProductById.setScreen_resolution(request.getScreenresolution());
		dbProductById.setReleasedate(request.getReleasedate());
		dbProductById.setRambusspeed(request.getRambusspeed());
		dbProductById.setRam_type(request.getRamtype());
		dbProductById.setRam(request.getRam());
		dbProductById.setStoragecapacity(request.getStoragecapacity());
		dbProductById.setOperatingsystem(request.getOperatingsystem());
		dbProductById.setWeight(request.getWeight());
		dbProductById.setGraphicscard(request.getGraphicscard());
		productRepo.save(dbProductById);
	}
	@Override
	public void DeleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		Product dbProductById=productRepo.findProductByProductIdAndIsdeleted(productId, IsDeleteStatus.NO.getCode());
		if(dbProductById==null)
		{
			throw new BadRequestException("Sản phẫm không tồn tại");
		}
		dbProductById.setIsdeleted(IsDeleteStatus.YES.getCode());
		productRepo.save(dbProductById);
	}
	@Override
	public void UpdateStatusProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		Product dbProductById=productRepo.findById(request.getProductId()).orElse(null);
		if(dbProductById==null)
		{
			throw new BadRequestException("Sản phẫm không tồn tại");
		}
		dbProductById.setIsdeleted(request.getIsDeleted());
		productRepo.save(dbProductById);
	}
	@Override
	public Product findProductById(Integer id) {
	    return productRepo.findById(id).orElseThrow(() -> new BadRequestException("Product not found"));
	}
	
	@Override
	public Page<Product> getListProductAdmin(Pageable pageable) {
		// TODO Auto-generated method stub
		//return productRepo.findAll(pageable);
        return productRepo.findProductsOrderByProductIdDesc(pageable);

	}
	@Override
	public Product getProductByIdAdmin(int id) {
		// TODO Auto-generated method stub
		return productRepo.findProductByProductId(id);
	}
	@Override
	public Page<Product> getListProductAdminByKeyword(Pageable pageable, String keyword) {
		// TODO Auto-generated method stub
		return productRepo.findByProductNameContaining(keyword, pageable);
	}
	
	@Override
    public List<Product> getFilteredProducts(String cpu, String graphicscard, String ram, String screensize, String storagecapacity, Integer fromPriceRange, Integer toPriceRange,String producer){
	        return productRepo.findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetweenAndProducerContaining(cpu, graphicscard, ram, screensize, storagecapacity, fromPriceRange, toPriceRange, producer);
	    }
	
	@Override
	public List<Product> findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetweenAndProducerContaining(
			String cpu, String graphicscard, String ram, String screenSize, String storageCapacity,
			Integer fromPriceRange, Integer toPriceRange,String producer) {
		// TODO Auto-generated method stub
		return productRepo.findByCpuContainingAndGraphicscardContainingAndRamContainingAndScreensizeContainingAndStoragecapacityContainingAndUnitpriceBetweenAndProducerContaining(cpu, graphicscard, ram, screenSize, storageCapacity, fromPriceRange, toPriceRange, producer);
	}
}

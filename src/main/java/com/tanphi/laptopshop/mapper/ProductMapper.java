package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.Reviews;
import com.tanphi.laptopshop.response.product.GetAllProductPageResponse;
import com.tanphi.laptopshop.response.product.GetProductResponse;
import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static List<GetProductResponse> toResponeGetAllProduct(List<Product> productList) throws ParseException
    {
        List<GetProductResponse> list=new ArrayList<>();
        for(Product product:productList) {
        	GetProductResponse tmp=toResponeGetProduct(product);
            list.add(tmp);
        }
        return list;
    }
    public static GetProductResponse toResponeGetProduct(Product product) throws ParseException
    {
    	GetProductResponse tmp = new GetProductResponse();
    	GetListReviewsResponse tmpReviews=new GetListReviewsResponse();
    	Set<Reviews> setReviews=product.getReviewsSet();
    	Double doubleRating=0.0;
    	if(setReviews.size()>0)
    	{
    		doubleRating=setReviews.stream().mapToDouble(Reviews::getRate).average().getAsDouble();
    	}
    	tmpReviews.setRating(doubleRating);
    	tmpReviews.setListReviews(ReviewsMapper.toResponeGetListReviews(product.getReviewsSet()));
    	tmp.setReviewsResponses(tmpReviews);
        tmp.setProductId(product.getProductId());
        tmp.setProductName(product.getProductName());
        tmp.setQuantity(product.getQuantity());
        tmp.setImage(product.getImage());
        tmp.setUnitprice(product.getUnitprice());
        tmp.setDiscount(product.getDiscount());
        tmp.setDescription(product.getDescription());
        tmp.setCpu(product.getCpu());
        tmp.setCores(product.getCores());
        tmp.setThreads(product.getThreads());
        tmp.setCpuspeed(product.getCpuspeed());
        tmp.setCache(product.getCache());
        tmp.setStoragecapacity(product.getStoragecapacity());
        tmp.setStoragetype(product.getStoragetype());
        tmp.setRam(product.getRam());
        tmp.setRamtype(product.getRamtype());
        tmp.setRambusspeed(product.getRambusspeed());
        tmp.setScreensize(product.getScreensize());
        tmp.setScreenresolution(product.getScreenresolution());
        tmp.setScreenrefreshrate(product.getScreenrefreshrate());
        tmp.setGraphicscard(product.getGraphicscard());
        tmp.setAudiotechnology(product.getAudiotechnology());
        tmp.setWeight(product.getWeight());
        tmp.setCasingmaterial(product.getCasingmaterial());
        tmp.setBattery(product.getBattery());
        tmp.setOperatingsystem(product.getOperatingsystem());
        tmp.setSoldQuantity(product.getSoldquantity());
        tmp.setReleasedate(product.getReleasedate());
        tmp.setProducer(product.getProducer());
        tmp.setIsdeleted(product.getIsdeleted());
        return tmp;
    }

	public static GetAllProductPageResponse<GetProductResponse> toResponeGetAllProductPage(List<Product> productList,Integer currentPage,Integer totalPage) throws ParseException {
		GetAllProductPageResponse<GetProductResponse> tmp=new GetAllProductPageResponse<>();
		tmp.setCurrentPage(currentPage);
		tmp.setTotalPage(totalPage);
		List<GetProductResponse> listProducts=toResponeGetAllProduct(productList);
		tmp.setListProducts(listProducts);
		return tmp;
	}
	
//	public static GetAllProductToResponseFilter
	
	public static GetAllProductPageResponse<Product> toResponeGetAllProductPageAdmin(List<Product> productList,Integer currentPage,Integer totalPage) throws ParseException {
		GetAllProductPageResponse<Product> tmp=new GetAllProductPageResponse<>();
		tmp.setCurrentPage(currentPage);
		tmp.setTotalPage(totalPage);
		tmp.setListProducts(productList);
		return tmp;
	}
	public GetProductResponse mapToGetProductResponse(Product product) {
        GetProductResponse response = new GetProductResponse();
        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setQuantity(product.getQuantity());
        response.setImage(product.getImage());
        response.setUnitprice(product.getUnitprice());
        response.setDiscount(product.getDiscount());
        response.setDescription(product.getDescription());
        response.setCpu(product.getCpu());
        response.setCores(product.getCores());
        response.setThreads(product.getThreads());
        response.setCpuspeed(product.getCpuspeed());
        response.setCache(product.getCache());
        response.setStoragecapacity(product.getStoragecapacity());
        response.setStoragetype(product.getStoragetype());
        response.setRam(product.getRam());
        response.setRamtype(product.getRamtype());
        response.setRambusspeed(product.getRambusspeed());
        response.setScreensize(product.getScreensize());
        response.setScreenresolution(product.getScreenresolution());
        response.setScreenrefreshrate(product.getScreenrefreshrate());
        response.setGraphicscard(product.getGraphicscard());
        response.setAudiotechnology(product.getAudiotechnology());
        response.setWeight(product.getWeight());
        response.setCasingmaterial(product.getCasingmaterial());
        response.setBattery(product.getBattery());
        response.setOperatingsystem(product.getOperatingsystem());
        response.setSoldQuantity(product.getSoldquantity());
        response.setReleasedate(product.getReleasedate());
        response.setProducer(product.getProducer());   
        response.setIsdeleted(product.getIsdeleted());   
        // Map other fields as needed
        return response;
    }
}

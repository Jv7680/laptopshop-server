package com.tanphi.laptopshop.mapper;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.WishList;
import com.tanphi.laptopshop.response.wishlist.WishlistResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;




@Component
public class WishlistMapper {
	 
	 private final ProductMapper productMapper;

	    public WishlistMapper(ProductMapper productMapper) {
	        this.productMapper = productMapper;
	    }

	    public WishlistResponse mapToResponse(WishList wishlist) {
	        WishlistResponse response = new WishlistResponse();
	        response.setWishlistId(wishlist.getWishlistId());
	        response.setProduct(productMapper.mapToGetProductResponse(wishlist.getProduct()));
	        return response;
	    }
	    
	
	
	public static WishList toWishListEntity(Accounts account,Product product)
    {
		WishList tmp=new WishList();
        tmp.setAccounts(account);
        tmp.setProduct(product);
        tmp.setIsDelete("NO");
        return tmp;
    }

    public static List<WishlistResponse> toResponeGetWishlist(Set<WishList> wishlist) throws ParseException
    {
        List<WishlistResponse> list=new ArrayList<>();
        for(WishList wishlists:wishlist) {
        	WishlistResponse tmp=toResponeGetWishList(wishlists);
            list.add(tmp);
        }
        return list;
    }

	public static WishlistResponse toResponeGetWishList(WishList wishlist) throws ParseException
    {
		WishlistResponse tmp=new WishlistResponse();
    	tmp.setAccountId(wishlist.getAccounts().getAccountId());
    	tmp.setProductId(wishlist.getProduct().getProductId());
        return tmp;
    }
	
	
    
}

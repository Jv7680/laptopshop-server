package com.tanphi.laptopshop.response.wishlist;
import com.tanphi.laptopshop.response.product.GetProductResponse;



import java.util.List;

import com.tanphi.laptopshop.entity.WishList;

import lombok.Data;

@Data
public class WishlistResponse {
	private Integer accountId;
	private Integer productId;
    private Integer wishlistId;
    private GetProductResponse product;



}

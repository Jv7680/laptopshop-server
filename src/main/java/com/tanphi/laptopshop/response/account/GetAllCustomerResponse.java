package com.tanphi.laptopshop.response.account;


import java.util.List;

import com.tanphi.laptopshop.response.product.GetProductResponse;
import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import lombok.Data;

@Data
public class GetAllCustomerResponse {
	
	 private Integer accountId;
	    private String username;
	    private String gmail;
	    private String firstname;
	    private String lastname;
	    private String address;
	    private String phonenumber;
	    private Integer provider;
	    private Integer isDeleted;

	
}

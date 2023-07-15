package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.WishList;
import com.tanphi.laptopshop.mapper.WishlistMapper;
import com.tanphi.laptopshop.repository.WishListRepo;
import com.tanphi.laptopshop.request.wishlist.WishlistRequest;
import com.tanphi.laptopshop.response.wishlist.WishlistResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.repository.ProductRepo;



@Service
public class WishListServiceImpl implements WishListService {

	@Autowired
	private WishListRepo WishListRepo;
	private final WishListRepo wishListRepo;
    private final AccountsRepo accountsRepo;
    private final ProductRepo ProductRepo;
    private final WishlistMapper wishlistMapper;


    public WishListServiceImpl(WishListRepo wishListRepo, AccountsRepo accountsRepo,ProductRepo ProductRepo,WishlistMapper wishlistMapper) {
        this.wishListRepo = wishListRepo;
        this.accountsRepo = accountsRepo;
        this.ProductRepo = ProductRepo;
        this.wishlistMapper = wishlistMapper;

    }


	@Override
	public void createWishList(WishlistRequest request) {
		  try {
		        Accounts account = accountsRepo.findAccountsByAccountId(request.getAccountId());
		        if (account == null) {
		            throw new RuntimeException("Account not found");
		        }

		        Product product = ProductRepo.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

		        WishList newWishlist = new WishList();
		        newWishlist.setAccounts(account);
		        newWishlist.setProduct(product);

		        wishListRepo.save(newWishlist);
		    } catch (Exception e) {
		        e.printStackTrace();
		        throw new RuntimeException("Failed to create wishlist");
		    }
	}
	 @Override
	    public List<WishlistResponse> getListWishListByAccounts(Accounts accounts) {
	        List<WishList> wishList = wishListRepo.findByAccounts(accounts);
	        return wishList.stream()
	                .map(wishlistMapper::mapToResponse)
	                .collect(Collectors.toList());
	    }

	@Override
	public void deleteWishList(Integer wishlistId) {
		WishListRepo.deleteById(wishlistId);
	}
}

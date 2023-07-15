package com.tanphi.laptopshop.controller;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.WishList;
import com.tanphi.laptopshop.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.tanphi.laptopshop.request.wishlist.WishlistRequest;
import com.tanphi.laptopshop.response.wishlist.WishlistResponse;
import com.tanphi.laptopshop.mapper.WishlistMapper;
import com.tanphi.laptopshop.repository.AccountsRepo;

import javax.validation.Valid;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@PreAuthorize("hasAuthority('CUSTOMER')")
@RequestMapping("/wishlist")

public class WishListController {

	@Autowired
    private final WishListService wishListService;
    private final AccountsRepo accountsRepo;

  
    public WishListController(WishListService wishListService,AccountsRepo accountsRepo) {
        this.wishListService = wishListService;
        this.accountsRepo = accountsRepo;

    }

    @PostMapping("/create")
	public ResponseEntity<?> createWishlist(@Valid @RequestBody WishlistRequest request,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
        wishListService.createWishList(request);
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setMessage("Viết phản hồi thành công");
		return ResponseEntity.ok(apiResponse);
	}
		
    

    @GetMapping("/wishlist/{accountId}")
    public ResponseEntity<List<WishlistResponse>> getWishlist(@PathVariable Integer accountId) {
        try {
            Accounts account = accountsRepo.findAccountsByAccountId(accountId);
            if (account == null) {
                throw new RuntimeException("Account not found");
            }

            List<WishlistResponse> wishlist = wishListService.getListWishListByAccounts(account);

            return new ResponseEntity<>(wishlist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{wishlistId}")
    public ResponseEntity<String> deleteWishList(@PathVariable Integer wishlistId) {
        wishListService.deleteWishList(wishlistId);
        return new ResponseEntity<>("Wish list deleted successfully", HttpStatus.OK);
    }
}

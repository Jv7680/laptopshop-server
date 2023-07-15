package com.tanphi.laptopshop.service;
import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.WishList;
import com.tanphi.laptopshop.request.wishlist.WishlistRequest;
import com.tanphi.laptopshop.response.wishlist.WishlistResponse;

import java.util.List;

public interface WishListService {
    void createWishList(WishlistRequest request);

    List<WishlistResponse> getListWishListByAccounts(Accounts accounts);

    void deleteWishList(Integer wishlistId);
}

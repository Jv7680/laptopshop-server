package com.tanphi.laptopshop.repository;
import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.WishList;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

	public interface WishListRepo extends JpaRepository<WishList,Integer> {


	    List<WishList> findByAccounts(Accounts accounts);

}

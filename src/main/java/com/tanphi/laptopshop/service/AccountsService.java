package com.tanphi.laptopshop.service;


import java.util.List;
import com.tanphi.laptopshop.entity.enums.Roles;
import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.request.authen.UpdateIsDeleteAccountRequest;
import com.tanphi.laptopshop.request.authen.UpdateProfileRequest;
import com.tanphi.laptopshop.request.product.ProductRequest;

public interface AccountsService {
    Accounts findAccountByGmail(String email);
    Accounts findAccountById(Integer id);
    void updateProfile(Integer customerId,UpdateProfileRequest request);
  
    List<Accounts> findAllUserAccounts(Integer roles);
	void UpdateStatusAccount(UpdateIsDeleteAccountRequest request);
	
}

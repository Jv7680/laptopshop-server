package com.tanphi.laptopshop.service;

import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.entity.Product;
import com.tanphi.laptopshop.entity.enums.IsDeleteStatus;
import com.tanphi.laptopshop.entity.enums.Roles;
import com.tanphi.laptopshop.exception.BadRequestException;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.request.authen.UpdateIsDeleteAccountRequest;
import com.tanphi.laptopshop.request.authen.UpdateProfileRequest;
import com.tanphi.laptopshop.request.product.ProductRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    AccountsRepo accountsRepo;
    @Override
    public Accounts findAccountByGmail(String email) {
        return accountsRepo.findAccountsByGmailAndActiveAccount(email, IsDeleteStatus.NO.getCode());
    }
	@Override
	public Accounts findAccountById(Integer id) {
		// TODO Auto-generated method stub
		return accountsRepo.findAccountsByAccountId(id);
	}
	@Override
	public void updateProfile(Integer customerId,UpdateProfileRequest request) {
		// TODO Auto-generated method stub
		Accounts updateAccount=findAccountById(customerId);
		updateAccount.setFirstname(request.getFirstName());
		updateAccount.setLastname(request.getLastName());
		updateAccount.setAddress(request.getAddress());
		accountsRepo.save(updateAccount);
	}
	
	@Override
    public List<Accounts> findAllUserAccounts(Integer roles) {
        return accountsRepo.findAllByRoles(Roles.CUSTOMER.getCode());
    }

	
	@Override
	public void UpdateStatusAccount(UpdateIsDeleteAccountRequest request) {
		// TODO Auto-generated method stub
		Accounts dbAccountById=accountsRepo.findById(request.getAccountId()).orElse(null);
		if(dbAccountById==null)
		{
			throw new BadRequestException("Người dùng không tồn tại");
		}
		dbAccountById.setIsdeleted(request.getIsDeleted());
		accountsRepo.save(dbAccountById);
	}
	
}

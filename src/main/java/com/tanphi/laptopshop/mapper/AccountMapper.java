package com.tanphi.laptopshop.mapper;


import java.util.ArrayList;
import java.util.List;



import com.tanphi.laptopshop.entity.Accounts;
import com.tanphi.laptopshop.response.account.GetAllCustomerResponse;


public class AccountMapper {
	public static GetAllCustomerResponse toResponeGetAllAccount(Accounts Accounts)
    {
		GetAllCustomerResponse tmp=new GetAllCustomerResponse();
		tmp.setAccountId(Accounts.getAccountId());
    	tmp.setUsername(Accounts.getUsername());
    	tmp.setGmail(Accounts.getGmail());
    	tmp.setFirstname(Accounts.getFirstname());
    	tmp.setLastname(Accounts.getLastname());
    	tmp.setAddress(Accounts.getAddress());
    	tmp.setPhonenumber(Accounts.getPhonenumber());
    	tmp.setProvider(Accounts.getProvider());
    	tmp.setIsDeleted(Accounts.getIsdeleted());


        return tmp;
    }
	public static List<GetAllCustomerResponse> toResponeGetAllAccount(List<Accounts> listAccounts)
    {
        List<GetAllCustomerResponse> list=new ArrayList<>();
        for(Accounts Accounts:listAccounts) {
        	GetAllCustomerResponse tmp=toResponeGetAllAccount(Accounts);
            list.add(tmp);
        }
        return list;
    }
}

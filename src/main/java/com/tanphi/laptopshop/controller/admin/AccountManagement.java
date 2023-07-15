package com.tanphi.laptopshop.controller.admin;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanphi.laptopshop.entity.enums.ActiveAccountStatus;
import com.tanphi.laptopshop.entity.enums.Roles;
import com.tanphi.laptopshop.mapper.AccountMapper;
import com.tanphi.laptopshop.mapper.OrdersMapper;
import com.tanphi.laptopshop.repository.AccountsRepo;
import com.tanphi.laptopshop.request.authen.UpdateIsDeleteAccountRequest;
import com.tanphi.laptopshop.request.product.ProductRequest;
import com.tanphi.laptopshop.response.account.GetAllCustomerResponse;
import com.google.common.base.Optional;
import com.tanphi.laptopshop.controller.ApiResponse;
import com.tanphi.laptopshop.entity.Accounts;

import com.tanphi.laptopshop.service.AccountsService;


@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("admin/account")

public class AccountManagement {
	private static final Integer Integer = null;

	@Autowired
    private AccountsService accountsService;


    @GetMapping("/users")
    public ResponseEntity<List<GetAllCustomerResponse>> getAllCustomers() {
    	List<Accounts> customers = accountsService.findAllUserAccounts(Roles.CUSTOMER.getCode());
    	
    	return ResponseEntity
				.ok(AccountMapper.toResponeGetAllAccount(customers));
    }
    
    
	@PutMapping("/updateStatus")
	public ResponseEntity<?> UpdateStatusProduct(@Valid @RequestBody UpdateIsDeleteAccountRequest request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.NOT_ACCEPTABLE);
		}
		accountsService.UpdateStatusAccount(request);
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("Xóa tài khoản người dùng thành công");
		return ResponseEntity.ok(apiResponse);
	}
//    @PutMapping("/{accountId}/active")
//    public ResponseEntity<?> updateActiveStatus(@PathVariable Integer accountId, @RequestBody ActiveAccountStatus activeAccountStatus) {
//        Accounts optionalAccounts = accountrepo.findAccountsByAccountId(Integer AccountID);
//        if (optionalAccounts.isPresent()) {
//            Accounts accounts = optionalAccounts.get();
//            accounts.setActiveAccount(activeAccountStatus.getCode());
//            accountrepo.save(accounts);
//            return ResponseEntity.ok("Active status updated successfully.");
//        }
//        return ResponseEntity.notFound().build();
//    }
}

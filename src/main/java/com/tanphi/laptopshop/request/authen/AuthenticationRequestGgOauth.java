package com.tanphi.laptopshop.request.authen;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthenticationRequestGgOauth {
	   @NotNull(message = "Vui lòng nhập tên tài khoản")
	    @NotEmpty(message = "Vui lòng nhập tên tài khoản")
	    private String username;
	   public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

}

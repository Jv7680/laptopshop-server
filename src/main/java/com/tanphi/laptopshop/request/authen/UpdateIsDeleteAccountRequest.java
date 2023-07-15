package com.tanphi.laptopshop.request.authen;

import lombok.Data;

@Data

public class UpdateIsDeleteAccountRequest {
    private Integer accountId;
    private Integer isDeleted;


}

package com.tanphi.laptopshop.request.paypal;

import lombok.Data;

@Data
public class PaymentRequest {
	private double total;
    private String currency;
    private String method;
    private String intent;
    private String description;
    private String cancelUrl;
    private String successUrl;
}

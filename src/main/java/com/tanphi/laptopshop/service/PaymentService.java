package com.tanphi.laptopshop.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tanphi.laptopshop.request.paypal.PaymentRequest;
import com.tanphi.laptopshop.response.paypal.PaymentResponse;

public interface PaymentService {
	PaymentResponse createPayment(PaymentRequest paymentRequest) throws PayPalRESTException;
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}

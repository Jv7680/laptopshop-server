package com.tanphi.laptopshop.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tanphi.laptopshop.request.paypal.PaymentRequest;
import com.tanphi.laptopshop.response.paypal.PaymentResponse;
import com.tanphi.laptopshop.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
    private PaymentService paymentService;

	@PostMapping("/create")
    public PaymentResponse createPayment(@RequestBody PaymentRequest paymentRequest) throws PayPalRESTException {
        return paymentService.createPayment(paymentRequest);
    }

	 @GetMapping("/execute")
	    public Payment executePayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException {
	        return paymentService.executePayment(paymentId, payerId);
	    }
}

package com.tanphi.laptopshop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.tanphi.laptopshop.request.paypal.PaymentRequest;
import com.tanphi.laptopshop.response.paypal.PaymentResponse;

@Service
public class PaymentServicelmpl implements PaymentService{
	private String clientId = "AS8GxdP6NIf6-FBELcPxwfcyV-dThJU9SI5FgGm0mGQYqf9M2daF1_rKZdaC0FYIZfOaEe6bp3CjP_xT";
    private String clientSecret = "EJ8zC94RwYoCFKoCLVlu3mLM9UhJYmXGcV_8Gvs3VcOzQ-LkFvQeMiEu9gagNXcCX7yjHV2RuaURrJGx";
    private String mode = "sandbox";
    private APIContext apiContext;
    private final String  BASE = "https://api-m.sandbox.paypal.com";
    
    private String getAuth(String client_id, String app_secret) {
        String auth = client_id + ":" + app_secret;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public String generateAccessToken() {
        String auth = this.getAuth(
        		clientId,
        		clientSecret
        );
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + auth);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(requestBody, headers);
        requestBody.add("grant_type", "client_credentials");

        ResponseEntity<String> response = restTemplate.postForEntity(
                BASE +"/v1/oauth2/token",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return new JSONObject(response.getBody()).getString("access_token");
        } else {
            return "Unavailable to get ACCESS TOKEN, STATUS CODE " + response.getStatusCode();
        }
    }
    
    public PaymentResponse createPayment(PaymentRequest paymentRequest) throws PayPalRESTException {
        String accestoken = generateAccessToken();
        String responseString = null;
        try {
            URL url = new URL("https://api-m.sandbox.paypal.com/v1/payments/payment");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accestoken);

            String requestBody = "{\r\n"
                    + "  \"intent\": \"" + paymentRequest.getIntent() + "\",\r\n"
                    + "  \"payer\": {\r\n"
                    + "    \"payment_method\": \"" + paymentRequest.getMethod() + "\"\r\n"
                    + "  },\r\n"
                    + "  \"transactions\": [\r\n"
                    + "    {\r\n"
                    + "      \"amount\": {\r\n"
                    + "        \"total\": \"" + paymentRequest.getTotal() + "\",\r\n"
                    + "        \"currency\": \"" + paymentRequest.getCurrency() + "\"\r\n"
                    + "      },\r\n"
                    + "      \"description\": \"" + paymentRequest.getDescription() + "\"\r\n"
                    + "    }\r\n"
                    + "  ],\r\n"
                    + "  \"redirect_urls\": {\r\n"
                    + "    \"return_url\": \"" + paymentRequest.getSuccessUrl() + "\",\r\n"
                    + "    \"cancel_url\": \"" + paymentRequest.getCancelUrl() + "\"\r\n"
                    + "  }\r\n"
                    + "}";

            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(requestBody);
            writer.flush();
            writer.close();

            int statusCode = connection.getResponseCode();
            if (statusCode != 201) {
            	 BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            	    StringBuilder errorStringBuilder = new StringBuilder();
            	    String errorLine = null;
            	    while ((errorLine = errorReader.readLine()) != null) {
            	        errorStringBuilder.append(errorLine + "\n");
            	    }
            	    String errorResponseString = errorStringBuilder.toString();
            	    throw new RuntimeException("Failed with HTTP error code : " + statusCode + " and error response : " + errorResponseString);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            responseString = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        PaymentResponse paymentResponse = gson.fromJson(responseString, PaymentResponse.class);
        return paymentResponse;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        String accestoken = generateAccessToken();
        try {
            // create request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("payer_id", payerId);

            // create execute payment request
            HttpPost executePaymentRequest = new HttpPost("https://api.sandbox.paypal.com/v1/payments/payment/" + paymentId + "/execute");
            executePaymentRequest.addHeader("Authorization", "Bearer " + accestoken);
            executePaymentRequest.addHeader("Content-Type", "application/json");
            executePaymentRequest.setEntity(new StringEntity(requestBody.toString()));

            // execute request
            CloseableHttpResponse response = httpClient.execute(executePaymentRequest);
            String responseBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            // check response status code
            if (statusCode != 200) {
                throw new Exception("Execute payment request failed with status code: " + statusCode + " and response body: " + responseBody);
            }

            // handle response
            PaymentResponse paymentResponse = new Gson().fromJson(responseBody, PaymentResponse.class);
            // do something with the paymentResponse object
        } catch (Exception ex) {
        	 ex.printStackTrace();
        } 
        if (httpClient != null) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
    }
}

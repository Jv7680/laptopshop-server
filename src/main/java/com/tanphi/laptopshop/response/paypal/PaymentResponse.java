package com.tanphi.laptopshop.response.paypal;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class PaymentResponse {
    private String id;
    private String intent;
    private String state;
    private Payer payer;
    private List<Transaction> transactions;
    @SerializedName("create_time")
    private String createTime;
    private List<Link> links;

    @Data
    public static class Payer {
        @SerializedName("payment_method")
        private String paymentMethod;
    }

    @Data
    public static class Transaction {
        private Amount amount;
        private String description;
        @SerializedName("related_resources")
        private List<RelatedResource> relatedResources;

        @Data
        public static class Amount {
            private String total;
            private String currency;
        }

        @Data
        public static class RelatedResource {
        }
    }

    @Data
    public static class Link {
        private String href;
        private String rel;
        private String method;
    }
}


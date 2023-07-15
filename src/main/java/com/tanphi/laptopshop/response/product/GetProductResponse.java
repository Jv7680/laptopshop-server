package com.tanphi.laptopshop.response.product;

import com.tanphi.laptopshop.response.reviews.GetListReviewsResponse;

import lombok.Data;

@Data
public class GetProductResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private String image;
    private Integer unitprice;
    private Integer discount;
    private String description;
//    private String CPU;
//    private String ram;
//    private String weight;
//    private String hardDiskCapacity;
//    private String hardDiskType;
//    private String screenSize;
//    private String screenResolution;
//    private String graphicCardName;
//    private String graphicCardMemory;
//    private String batteryCapacity;
//    private String osSupplied;
    private String cpu;
    private String cores;
    private String threads;
    private String cpuspeed;
    private String cache;
    private String storagecapacity;
    private String storagetype;
    private String ram;
    private String ramtype;
    private String rambusspeed;
    private String screensize;
    private String screenresolution;
    private String screenrefreshrate;
    private String graphicscard;
    private String audiotechnology;
    private String weight;
    private String casingmaterial;
    private String battery;
    private String operatingsystem;
    private Integer soldQuantity;
    private Integer releasedate;
    private String producer;
    private Integer isdeleted;

    
    private GetListReviewsResponse reviewsResponses;

}

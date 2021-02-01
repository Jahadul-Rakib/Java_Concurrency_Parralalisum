package com.rakib.java_concurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private Integer infoID;
    private Integer productID;
    private String productName;
    private Integer productPrice;
}

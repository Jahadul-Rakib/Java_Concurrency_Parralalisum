package com.rakib.java_concurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {
    private Integer reviewID;
    private Integer productID;
    private String review;
    private Integer userID;
}

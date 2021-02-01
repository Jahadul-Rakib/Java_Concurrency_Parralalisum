package com.rakib.java_concurrency.service;

import com.rakib.java_concurrency.model.ProductReview;
import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductReviewService {
    private List<ProductReview> productReviewList = List.of(
            new ProductReview(1, 1, "Good", 21300),
            new ProductReview(2, 2, "Bad", 402),
            new ProductReview(3, 3, "Good", 130),
            new ProductReview(4, 4, "Good", 4480),
            new ProductReview(5, 5, "Bad", 1350),
            new ProductReview(6, 6, "Good", 2600),
            new ProductReview(7, 7, "Good", 3700),
            new ProductReview(8, 8, "Bad", 33800)
    );

    public ProductReview retriveProductReview(Integer id) {
        CommonUtils.delay(1000);
        Optional<ProductReview> productReview = productReviewList.stream().filter(v -> v.getProductID().equals(id)).findAny();
        return productReview.get();
    }
}

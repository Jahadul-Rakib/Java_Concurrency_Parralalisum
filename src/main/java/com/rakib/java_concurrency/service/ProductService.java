package com.rakib.java_concurrency.service;

import com.rakib.java_concurrency.model.Product;
import com.rakib.java_concurrency.model.ProductInfo;
import com.rakib.java_concurrency.model.ProductReview;
import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductInfoService productInfoService;
    private final ProductReviewService productReviewService;

    public ProductService(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    public Product retriveProductDetails(Integer productId) {
        CommonUtils.stopWatch.start();
        ProductInfo productInfo = productInfoService.retriveProductInfo(productId);
        ProductReview productReview = productReviewService.retriveProductReview(productId);
        CommonUtils.stopWatch.stop();
        System.out.println(CommonUtils.stopWatch.getTime());
        return Product.builder()
                .productInfo(productInfo)
                .productReview(productReview)
                .build();
    }
}

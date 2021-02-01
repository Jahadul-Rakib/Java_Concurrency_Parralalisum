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

    public Product retriveProductDetails(Integer productId) throws InterruptedException {
        CommonUtils.stopWatch.start();

        ProductInfoRunnable productInfoData = new ProductInfoRunnable(productId);
        Thread productInfoThread = new Thread(productInfoData);
        productInfoThread.start();


        ProductReviewRunnable productReviewData = new ProductReviewRunnable(productId);
        Thread productReviewThread = new Thread(productReviewData);
        productReviewThread.start();

        productInfoThread.join();
        productReviewThread.join();

        CommonUtils.stopWatch.stop();
        System.out.println(CommonUtils.stopWatch.getTime());
        return Product.builder()
                .productInfo(productInfoData.getProductInfo())
                .productReview(productReviewData.getProductReview())
                .build();
    }

    private class ProductInfoRunnable implements Runnable {
        public Integer productId;
        private ProductInfo productInfo;

        public ProductInfoRunnable(Integer productId) {
            this.productId = productId;
        }

        public ProductInfo getProductInfo() {
            return productInfo;
        }

        @Override
        public void run() {
            this.productInfo = productInfoService.retriveProductInfo(productId);
        }
    }

    private class ProductReviewRunnable implements Runnable {
        public Integer productId;
        private ProductReview productReview;

        public ProductReviewRunnable(Integer productId) {
            this.productId = productId;
        }

        public ProductReview getProductReview() {
            return productReview;
        }

        @Override
        public void run() {
            this.productReview = productReviewService.retriveProductReview(productId);
        }
    }
}

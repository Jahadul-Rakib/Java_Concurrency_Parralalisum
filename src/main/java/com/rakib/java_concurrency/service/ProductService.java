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
    ProductInfo productInfo;
    ProductReview productReview;

    public ProductService(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    public Product retriveProductDetails(Integer productId) throws InterruptedException {
        CommonUtils.stopWatch.start();

//        Runnable runnable1 = () -> productInfo = productInfoService.retriveProductInfo(productId);
//        Runnable runnable2 = () -> productReview = productReviewService.retriveProductReview(productId);
//        runnable1.run();
//        runnable2.run();

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
                .productInfo(productInfo)
                .productReview(productReview)
                .build();
    }

    private class ProductInfoRunnable implements Runnable {
        public Integer productId;

        public ProductInfoRunnable(Integer productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            productInfo = productInfoService.retriveProductInfo(productId);
        }
    }

    private class ProductReviewRunnable implements Runnable {
        public Integer productId;

        public ProductReviewRunnable(Integer productId) {
            this.productId = productId;
        }

        @Override
        public void run() {
            productReview = productReviewService.retriveProductReview(productId);
        }
    }
}

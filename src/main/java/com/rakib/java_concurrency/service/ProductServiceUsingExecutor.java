package com.rakib.java_concurrency.service;

import com.rakib.java_concurrency.model.Product;
import com.rakib.java_concurrency.model.ProductInfo;
import com.rakib.java_concurrency.model.ProductReview;
import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class ProductServiceUsingExecutor {
    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final ProductInfoService productInfoService;
    private final ProductReviewService productReviewService;

    public ProductServiceUsingExecutor(ProductInfoService productInfoService, ProductReviewService productReviewService) {
        this.productInfoService = productInfoService;
        this.productReviewService = productReviewService;
    }

    public Product retriveProductDetails(Integer productId) throws InterruptedException, ExecutionException {
        CommonUtils.stopWatch.start();

        Future<ProductInfo> productInfoFuture = executorService.submit(() -> productInfoService.retriveProductInfo(productId));
        Future<ProductReview> productReviewFuture = executorService.submit(() -> productReviewService.retriveProductReview(productId));

        //executorService service do not go shutdown automatically
        executorService.shutdown();

        CommonUtils.stopWatch.stop();
        System.out.println("Total Time: "+CommonUtils.stopWatch.getTime());
        return Product.builder()
                .productInfo(productInfoFuture.get()/*But Still Block for calling get() method and wait for return value*/)
                .productReview(productReviewFuture.get()/*But Still Block for calling get() method and wait for return value*/)
                .build();

        //CompletableFuture Can Solve this Blocking Issue.
    }
}

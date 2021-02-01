package com.rakib.java_concurrency.service;

import com.rakib.java_concurrency.model.ProductInfo;
import com.rakib.java_concurrency.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoService {

    private List<ProductInfo> productInfoList = List.of(
            new ProductInfo(1, 1, "Gas", 2300),
            new ProductInfo(2, 2, "Banana", 40),
            new ProductInfo(3, 3, "Pen", 10),
            new ProductInfo(4, 4, "Light", 480),
            new ProductInfo(5, 5, "Rice", 130),
            new ProductInfo(6, 6, "Apple", 200),
            new ProductInfo(7, 7, "Mango", 300),
            new ProductInfo(8, 8, "Table", 3300)
    );

    public ProductInfo retriveProductInfo(Integer id) {
        CommonUtils.delay(1000);
        Optional<ProductInfo> productInfo = productInfoList.stream().filter(v -> v.getProductID().equals(id)).findAny();
        return productInfo.get();
    }
}

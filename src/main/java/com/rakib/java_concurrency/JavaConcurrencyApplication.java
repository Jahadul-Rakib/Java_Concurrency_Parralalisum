package com.rakib.java_concurrency;

import com.rakib.java_concurrency.model.Product;
import com.rakib.java_concurrency.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaConcurrencyApplication implements CommandLineRunner {
    @Autowired
    ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(JavaConcurrencyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product = productService.retriveProductDetails(1);
        System.out.println(product);
    }
}

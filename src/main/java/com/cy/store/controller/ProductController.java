package com.cy.store.controller;


import com.cy.store.pojo.Product;
import com.cy.store.service.impl.ProductServiceImpl;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/hot_product")
    public JsonResult<List<Product>> getHotProduct(){
        List<Product> data = productService.findHotList();
        return new JsonResult<>(State.OK,data);
    }

}

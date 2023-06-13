package com.cy.store.service;

import com.cy.store.pojo.Cart;
import com.cy.store.pojo.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void addCart(){
        Cart cart = new Cart();
        cart.setUid(4);
        cart.setPid(1);
        cart.setNum(1);
        cart.setPrice(100);
        cart.setModifiedUser("喵喵");
        cartService.addCart(cart);
    }
}

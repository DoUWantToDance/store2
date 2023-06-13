package com.cy.store.controller;

import com.cy.store.pojo.Cart;
import com.cy.store.service.impl.CartServiceImpl;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import com.cy.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController extends BaseController{

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/add_cart")
    public JsonResult<Void> addCart(@RequestBody Cart cart, HttpSession session){
        cart.setModifiedUser(getUsernameFromSession(session));
        cartService.addCart(cart);
        return new JsonResult<Void>(State.OK);
    }

    @GetMapping("/get_cart")
    public JsonResult<List<CartVO>> getCart(HttpSession session){
        List<CartVO> carts = cartService.getCart(getUidFromSession(session));
        return new JsonResult<>(State.OK,"",carts);
    }

    @PutMapping("/update_num")
    public JsonResult<Integer> updateNum(Integer cid,Integer num,HttpSession session){
        Integer data = cartService.updateCart(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(State.OK,data);
    }

    @GetMapping("/get_cart_by_cid")
    public JsonResult<List<CartVO>> getCartVOByCid(Integer[] cids,HttpSession session){
        List<CartVO> data = cartService.getCartByCid(cids, getUidFromSession(session));
        return new JsonResult<>(State.OK,data);
    }
}

package com.cy.store.service;

import com.cy.store.pojo.Cart;
import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {


    public void addCart(Cart cart);

    public List<CartVO> getCart(Integer uid);

    public Integer updateCart(Integer cid, Integer uid, String modifiedUser);

    public List<CartVO> getCartByCid(Integer[] cids, Integer uid);

}

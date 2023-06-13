package com.cy.store.service;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.Order;
import org.apache.ibatis.ognl.ASTIn;

public interface IOrderService {


    Order addOrder(Integer aid, Integer uid, Integer[] cids, String username);
}

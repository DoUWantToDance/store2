package com.cy.store.controller;

import com.cy.store.pojo.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @PostMapping("/add")
    public JsonResult<Order> addOrder(Integer aid, Integer[] cids, HttpSession session){
        Order data = orderService.addOrder(aid, getUidFromSession(session), cids, getUsernameFromSession(session));
        return new JsonResult<>(State.OK,data);
    }


}

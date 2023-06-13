package com.cy.store.service.impl;

import com.cy.store.mapper.OrderMapper;
import com.cy.store.pojo.Address;
import com.cy.store.pojo.Order;
import com.cy.store.pojo.OrderItem;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICartService cartService;


    @Override
    @Transactional
    public Order addOrder(Integer aid, Integer uid, Integer[] cids, String username) {
        List<CartVO> list = cartService.getCartByCid(cids, uid);
        Integer totalPrice = 0;
        for(CartVO c : list){
            totalPrice += c.getRealPrice()*c.getNum();
        }
        Address address = addressService.getByAid(aid,uid);
        Order order = new Order();
        order.setUid(uid);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        order.setRecvName(username);
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setRecvPhone(address.getPhone());
        order.setStatus(0);
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        Integer rows = orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("数据插入时异常！");
        }

        for(CartVO c : list){
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(c.getPid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setNum(c.getNum());
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);

            rows = orderMapper.insertOrderItem(orderItem);
            if(rows!=1){
                throw new InsertException("数据插入时异常！");
            }
        }

        return order;
    }
}

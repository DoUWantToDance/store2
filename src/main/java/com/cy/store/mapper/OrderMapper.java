package com.cy.store.mapper;

import com.cy.store.pojo.Order;
import com.cy.store.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    public Integer insertOrder(Order order);

    public Integer insertOrderItem(OrderItem orderItem);

}

package com.cy.store.mapper;

import com.cy.store.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 热门商品列表
     * @return
     */
    List<Product> findHotList();
}

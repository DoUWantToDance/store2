package com.cy.store.service;

import com.cy.store.mapper.AddressMapper;
import com.cy.store.pojo.Address;

import java.util.List;

public interface IAddressService {

    /**
     * 创建收货地址
     * @param uid
     * @param username 创建人
     * @param address
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 根据uid查询
     * @param uid
     * @return 地址数据
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置默认地址
     * @param aid
     * @param uid
     * @param modifiedUser
     */
    void updateDefault(Integer aid, Integer uid, String modifiedUser);

    /**
     * 删除地址信息
     * @param aid
     * @param uid
     * @param modifiedUser
     */
    void deleteByAid(Integer aid, Integer uid, String modifiedUser);


    /**
     *
     * @param aid
     * @return
     */
    Address getByAid(Integer aid, Integer uid);

}

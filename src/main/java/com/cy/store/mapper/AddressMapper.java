package com.cy.store.mapper;

import com.cy.store.pojo.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AddressMapper {

    /**
     * 插入收货地址
     * @param address
     * @return 受影响行数
     */
    Integer insert(Address address);

    /**
     * 用户收货地址数量
     * @param uid
     * @return 收货地址数量
     */
    Integer countByUid(Integer uid);

    /**
     * 根据uid查询用户收货地址数据
     * @param uid
     * @return 地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     *
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     *
     * @param uid
     * @return
     */
    Integer updateNonDefault(Integer uid);

    /**
     *
     * @return
     */
    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     *
     * @param aid
     * @return
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     *
     * @return
     */
    Address findByModifiedTimeAddress(Integer uid);

}

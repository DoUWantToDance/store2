package com.cy.store.service;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void addNewAddress(){
        Address address = new Address();
        address.setName("lzx");
        address.setProvinceName("sc");
        address.setProvinceCode("11");
        address.setCityName("cd");
        address.setCityCode("111");
        address.setAreaName("xd");
        address.setAreaCode("1111");
        address.setZip("123456");
        address.setAddress("123456");
        address.setPhone("114514");
        address.setTel("1111111");
        address.setTag("000");
        address.setIsDefault(1);
        addressService.addNewAddress(1,"lzx",address);

    }

    @Test
    public void updateDefault(){
        addressService.updateDefault(2,1,"lzx");
    }

    @Test
    public void deleteByAid(){
        addressService.deleteByAid(4,4,"喵喵");
    }

}

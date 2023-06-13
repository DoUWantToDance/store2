package com.cy.store.mapper;

import com.cy.store.pojo.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert(){
        Address address = new Address();
        address.setUid(1);
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
        address.setCreatedUser("lzx");
        address.setCreatedTime(new Date());
        address.setModifiedUser("lzx");
        address.setModifiedTime(new Date());
        Integer rows = addressMapper.insert(address);
        System.out.println(rows);
    }

    @Test
    public void countByUid(){
        Integer rows = addressMapper.countByUid(1);
        System.out.println(rows);
    }

    @Test
    public void findByUid(){
        List<Address> list = addressMapper.findByUid(4);
        for (Address a : list){
            System.out.println(a.toString());
        }
    }

    @Test
    public void findByAid(){
        Address address = addressMapper.findByAid(1);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault(){
        Integer rows = addressMapper.updateNonDefault(4);
        System.out.println(rows);
    }

    @Test
    public void updateDefault(){
        Integer rows = addressMapper.updateDefaultByAid(1, "喵喵", new Date());
        System.out.println(rows);
    }

    @Test
    public void deleteByAid(){
        Integer rows = addressMapper.deleteAddressByAid(1);
        System.out.println(rows);
    }

    @Test
    public void findByModifiedTime(){
        Address add = addressMapper.findByModifiedTimeAddress(4);
        System.out.println(add);
    }


}

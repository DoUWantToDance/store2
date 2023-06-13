package com.cy.store.service;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DistrictServiceTests {

    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent(){
        List<District> list = districtService.getByParent("86");
        for (District d : list){
            System.out.println(d.toString());
        }
    }
}

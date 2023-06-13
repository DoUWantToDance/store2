package com.cy.store.mapper;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class DistrictMapperTests {

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> districts = districtMapper.findByParent("110100");
        for(District d : districts){
            System.out.println(d.toString());
        }
    }

}

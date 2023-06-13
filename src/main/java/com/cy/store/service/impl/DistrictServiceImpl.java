package com.cy.store.service.impl;

import com.cy.store.mapper.DistrictMapper;
import com.cy.store.pojo.District;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for(District d : list){
            d.setParent(null);
            d.setId(null);
        }
        return list;
    }
}

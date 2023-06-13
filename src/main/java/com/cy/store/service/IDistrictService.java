package com.cy.store.service;

import com.cy.store.pojo.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据父代号查询
     * @param parent
     * @return 多个区域代号
     */
    List<District> getByParent(String parent);
}

package com.cy.store.mapper;

import com.cy.store.pojo.Address;
import com.cy.store.pojo.District;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistrictMapper {

    /**
     * 根据父代码查询
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

}

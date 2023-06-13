package com.cy.store.controller;


import com.cy.store.pojo.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService districtService;

    @GetMapping("/{parent}")
    public JsonResult<List<District>> getByParent(@PathVariable String parent) {
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<>(State.OK,list);
    }

}

package com.cy.store.controller;


import com.cy.store.pojo.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import com.cy.store.util.State;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController{

    @Autowired
    private IAddressService addressService;

    @PostMapping("/add_new_address")
    public JsonResult<Void> addNewAddress(@RequestBody Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(State.OK);
    }

    @GetMapping("/get_by_uid")
    public JsonResult<List<Address>> getByUid(HttpSession session){
        List<Address> list = addressService.getByUid(getUidFromSession(session));
        return new JsonResult<>(State.OK,list);
    }

    @PutMapping("/update_default")
    public JsonResult<Void> updateDefault(Integer aid, HttpSession session){
        addressService.updateDefault(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(State.OK);
    }

    @DeleteMapping("/delete")
    public JsonResult<Void> deleteAddressByAid(Integer aid,HttpSession session){
        addressService.deleteByAid(aid,getUidFromSession(session),getUsernameFromSession(session));
        return new JsonResult<>(State.OK);
    }


}

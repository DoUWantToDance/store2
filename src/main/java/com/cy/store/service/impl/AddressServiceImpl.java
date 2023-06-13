package com.cy.store.service.impl;

import com.cy.store.mapper.AddressMapper;
import com.cy.store.pojo.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max_count}")
    private Integer maxCount;

    @Override
    @Transactional
    public void addNewAddress(Integer uid, String username, Address address) {

        Integer count = addressMapper.countByUid(uid);
        if (count>maxCount) {
            throw new AddressCountLimitException("收货地址数量超出上限！");
        }
        address.setUid(uid);
        address.setIsDefault(count==0 ? 1 : 0);
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);

        if(rows!=1){
            throw new InsertException("插入数据时异常！");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for(Address a : list){
            a.setProvinceCode(null);
            a.setCityCode(null);
            a.setAreaCode(null);
            a.setPhone(null);
            a.setTel(null);
            a.setCreatedTime(null);
            a.setCreatedUser(null);
            a.setIsDefault(null);
            a.setModifiedTime(null);
            a.setModifiedUser(null);
        }
        return list;
    }

    @Override
    @Transactional
    public void updateDefault(Integer aid, Integer uid, String modifiedUser) {

        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("收货地址不存在！");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("数据更新时异常！");
        }
        rows = addressMapper.updateDefaultByAid(aid, modifiedUser, new Date());
        if(rows!=1){
            throw new UpdateException("数据更新时异常！");
        }
    }

    @Override
    @Transactional
    public void deleteByAid(Integer aid, Integer uid, String modifiedUser) {

        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("地址不存在！");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        Integer rows = addressMapper.deleteAddressByAid(aid);
        if(rows!=1){
            throw new DeleteException("数据删除时异常！");
        }
        if(addressMapper.countByUid(uid)==0 || result.getIsDefault()==0){
            System.out.println(addressMapper.countByUid(uid));
            System.out.println(result.getIsDefault());
            return;
        }
        Address address = addressMapper.findByModifiedTimeAddress(uid);
        rows = addressMapper.updateDefaultByAid(address.getAid(), modifiedUser, new Date());
        if(rows!=1){
            throw new UpdateException("数据更新时异常！");
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if(result==null){
            throw new AddressNotFoundException("地址数据不存在！");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        result.setProvinceCode(null);
        result.setCityCode(null);
        result.setAreaCode(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }
}

package com.cy.store.service.impl;

import com.cy.store.mapper.CartMapper;
import com.cy.store.pojo.Cart;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    @Transactional
    public void addCart(Cart cart) {
        Cart result = cartMapper.findCartByUidAndPid(cart.getUid(),cart.getPid());
        if(result==null){
            Integer rows = cartMapper.insertCart(cart);
            if(rows!=1){
                throw new InsertException("数据插入时异常！");
            }
            return;
        }
        Integer rows = cartMapper.updateNum(result.getCid(), cart.getNum(), cart.getModifiedUser(), new Date());
        if(rows!=1){
            throw new UpdateException("数据更新时异常！");
        }
    }

    @Override
    public List<CartVO> getCart(Integer uid) {
        List<CartVO> result = cartMapper.findByUid(uid);
        return result;
    }

    @Override
    @Transactional
    public Integer updateCart(Integer cid, Integer uid, String modifiedUser) {
        Cart result = cartMapper.findByCid(cid);
        if(result==null){
            throw new CartNotFoundException("数据不存在！");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问！");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNum(cid, 1, modifiedUser, new Date());
        if(rows!=1){
            throw new UpdateException("数据更新时异常！");
        }
        return num;
    }

    @Override
    public List<CartVO> getCartByCid(Integer[] cids, Integer uid) {
        List<CartVO> result = cartMapper.findCartVOByCid(cids);
        Iterator<CartVO> it = result.iterator();
        while(it.hasNext()){
            CartVO cartVO = it.next();
            if(!cartVO.getUid().equals(uid)){
                result.remove(cartVO);
            }
        }
        return result;
    }
}

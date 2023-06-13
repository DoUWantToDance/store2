package com.cy.store.mapper;

import com.cy.store.pojo.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {

    /**
     *
     * @param cart
     * @return
     */
    Integer insertCart(Cart cart);

    /**
     *
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNum(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     *
     * @param uid
     * @param pid
     * @return
     */
    Cart findCartByUidAndPid(Integer uid, Integer pid);

    /**
     *
     * @param uid
     * @return
     */
    List<CartVO> findByUid(Integer uid);

    /**
     *
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    /**
     *
     * @param cids
     * @return
     */
    List<CartVO> findCartVOByCid(Integer[] cids);

}

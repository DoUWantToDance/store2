package com.cy.store.controller;

import jakarta.servlet.http.HttpSession;

public class BaseController {


    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前登录用户的uid
     */
    protected final Integer getUidFromSession(HttpSession session) {

        return Integer.valueOf(session.getAttribute("uid").toString());
    }


    /**
     * 获取session对象中的username
     * @param session
     * @return 当前登录用户的username
     */
    protected final String getUsernameFromSession(HttpSession session) {

        return session.getAttribute("username").toString();
    }


}

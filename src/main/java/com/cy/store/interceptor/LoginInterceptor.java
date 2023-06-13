//package com.cy.store.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//public class LoginInterceptor implements HandlerInterceptor {
//
//    /**
//     * 检测全局session是否有uid数据
//     * @param request 请求对象
//     * @param response 响应对象
//     * @param handler 处理器
//     * @return ture放行，false拦截
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        Object obj = request.getSession().getAttribute("uid");
//        if(obj==null){
//            response.sendRedirect("/web/login.html");
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//
//}

package com.hos.hospital.config;

import com.hos.hospital.entity.Admin;
import com.hos.hospital.entity.Doctor;
import com.hos.hospital.entity.Patient;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 自定义拦截器
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session =  request.getSession();
        Doctor      doctor  =  (Doctor) session.getAttribute("DOCTOR");
        Admin       admin   =  (Admin) session.getAttribute("ADMIN");
        Patient       patient   =  (Patient) session.getAttribute("PATIENT");
        if (doctor != null || admin != null|| patient != null ) {
            return true;
        }
        //重定向路径
        response.sendRedirect("/login/afterView");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //拦截器-方法返回后
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //拦截器-页面渲染后
    }
}

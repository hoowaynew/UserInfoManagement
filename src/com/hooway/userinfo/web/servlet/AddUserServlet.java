package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        // 2.获取表单信息并封装
        Map<String, String[]> form_map = request.getParameterMap();
        User add_user = new User();
        try {
            BeanUtils.populate(add_user, form_map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 3.调用Service层方法执行添加用户
        UsersService usersService = new UsersServiceImpl();
        usersService.addUser(add_user);
        // 4.跳转查询所有用户
        response.sendRedirect(request.getContextPath() + "/findPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

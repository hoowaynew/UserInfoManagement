package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.service.UserService;
import com.hooware.userinfo.service.impl.UserServiceImpl;
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
        // 1.设置request编码
        request.setCharacterEncoding("utf-8");
        // 2.获取用户表单提交数据
        Map<String, String[]> add_map = request.getParameterMap();
        // 3.封装成用户
        User user = new User();
        UserService userService = new UserServiceImpl();
        try {
            BeanUtils.populate(user, add_map);
            // 4.添加用户数据
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 跳转到查询界面
        response.sendRedirect(request.getContextPath() + "/findAllUserServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

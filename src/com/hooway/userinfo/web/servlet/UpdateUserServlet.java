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

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置request编码
        request.setCharacterEncoding("utf-8");
        // 2.获取需要用户修改的信息
        Map<String, String[]> userModify_map = request.getParameterMap();
        // 3.封装成User
        User user = new User();
        try {
            BeanUtils.populate(user, userModify_map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 4.调用业务层进行用户信息更新
        UsersService usersService = new UsersServiceImpl();
        usersService.updateUserById(user);
        // 5.重定向跳转
        response.sendRedirect(request.getContextPath() + "/findPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

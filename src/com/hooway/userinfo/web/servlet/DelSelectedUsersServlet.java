package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedUsersServlet")
public class DelSelectedUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取表单提交的checkbox选中ID数组
        String[] ids = request.getParameterValues("tableCb");
        // 2.调用Service层进行删除用户操作
        UsersService usersService = new UsersServiceImpl();
        usersService.deleteSelectedUser(ids);
        // 3.重定向到查询用户界面
        response.sendRedirect(request.getContextPath() + "/findPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

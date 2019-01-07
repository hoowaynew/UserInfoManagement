package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取待删除条目的id
        String id = request.getParameter("id");
        // 2.调用service层删除此数据
        UsersService usersService = new UsersServiceImpl();
        usersService.deleteUser(id);
        // 3.跳转到查询所有用户信息界面
        response.sendRedirect(request.getContextPath() + "/findPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

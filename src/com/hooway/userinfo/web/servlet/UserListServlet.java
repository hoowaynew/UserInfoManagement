package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用service层返回所有用户列表
        UsersService usersService = new UsersServiceImpl();
        List<User> users = usersService.findAll();
        // 2.存入request域
        request.setAttribute("users", users);
        // 3.进行转发
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

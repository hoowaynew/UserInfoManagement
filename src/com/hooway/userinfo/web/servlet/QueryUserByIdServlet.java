package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/queryUserByIdServlet")
public class QueryUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取需要修改用户的id
        String id = request.getParameter("id");
        // 2.调用service层查询此id用户信息
        UsersService usersService = new UsersServiceImpl();
        User user = usersService.queryUserById(id);
        // 3.保存查询的信息到request域
        request.setAttribute("user",user);
        // 4.重定向到update.jsp页面进行回显
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

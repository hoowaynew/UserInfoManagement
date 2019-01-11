package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.service.UserService;
import com.hooware.userinfo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有用户信息Servlet
 */
@WebServlet("/findAllUserServlet")
public class FindAllUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询所有用户信息
        UserService userService = new UserServiceImpl();
        List<User> list = userService.findAllUSer();
        // 保存到域对象
        request.setAttribute("list", list);
        //转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

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

/**
 * 更新用户信息之前进行待修改信息的回显
 */
@WebServlet("/echoUserInfoServlet")
public class EchoUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取待修改用户的唯一ID
        String id = request.getParameter("id");
        // 2.根据id查询该用户信息
        UserService userService = new UserServiceImpl();
        User user = userService.searchUserInfoById(id);
        // 3.保存user到域对象并进行转发
        request.setAttribute("user",user);
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

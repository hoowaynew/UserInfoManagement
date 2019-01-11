package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.service.UserService;
import com.hooware.userinfo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/deleteSelectedUserServlet")
public class DeleteSelectedUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取待删除的用户id
        String[] ids = request.getParameterValues("id");
//        System.out.println(Arrays.toString(ids));
        // 2.调用service层进行删除选中操作
        if (ids != null) {
            UserService userService = new UserServiceImpl();
            userService.deleteSelectedUser(ids);
        }
        // 3.进行重定向跳转
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

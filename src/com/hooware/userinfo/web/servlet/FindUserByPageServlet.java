package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.domain.PageBean;
import com.hooware.userinfo.domain.User;
import com.hooware.userinfo.service.UserService;
import com.hooware.userinfo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 分页查询servlet
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.获取用户请求的页码和每页多少条数据
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        // 2.增加基本判断
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "6";
        }
        // 2.1 增加复杂查询,获取查询参数,并保存到request域中
        Map<String, String[]> searchMap = request.getParameterMap();
        request.setAttribute("searchMap", searchMap);

        // 3.调用service层进行分页查询 + 复杂查询
        UserService userService = new UserServiceImpl();
        PageBean<User> pb = userService.findUserByPage(currentPage, rows, searchMap);
        System.out.println(pb);
        // 4.保存pageBean对象到域和转发
        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

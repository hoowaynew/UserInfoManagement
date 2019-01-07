package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.domain.PageBean;
import com.hooway.userinfo.domain.User;
import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findPageServlet")
public class FindPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 1.获取前端传递的参数
        String currentPage = request.getParameter("currentPage");   // 当前页面
        String rows = request.getParameter("rows");                 // 页数数据条数
        if (currentPage == null ||"".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null ||"".equals(rows)) {
            rows = "5";
        }

        // 1.1 获取查询的Map集合
        Map<String, String[]> searchMap = request.getParameterMap();

        // 2.调用service层封装pageBean对象
        UsersService usersService = new UsersServiceImpl();
        PageBean<User> pb = usersService.findUserByPage(currentPage, rows, searchMap);

        // 3.保存pb对象到request域并转发到list.jsp
        request.setAttribute("pb", pb);
        request.setAttribute("searchMap", searchMap);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.domain.Administrator;
import com.hooware.userinfo.service.UserService;
import com.hooware.userinfo.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("utf-8");
        // 1.获取用户登陆提交的验证码
        String verifycode = request.getParameter("verifycode");
        // 2.获取servlet生成的验证码
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        // 3.校验验证码的正确与否
        if (checkCode == null || !checkCode.equalsIgnoreCase(verifycode)) {   // 校验失败
            request.setAttribute("login_msg","验证码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        // 4.验证码通过,开始验证用户名密码
        Map<String, String[]> login_map = request.getParameterMap();
        Administrator login_admin = new Administrator();
        UserService userService = new UserServiceImpl();
        try {
            BeanUtils.populate(login_admin, login_map);
//            System.out.println(login_admin);
            Administrator admin = userService.findAdmin(login_admin);
//            System.out.println("admin=" + admin);
            if (admin == null) {
                request.setAttribute("login_msg","用户名或密码输入错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            } else {
                request.getSession().setAttribute("admin", admin);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

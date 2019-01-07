package com.hooway.userinfo.web.servlet;

import com.hooway.userinfo.domain.Administrator;
import com.hooway.userinfo.service.UsersService;
import com.hooway.userinfo.service.impl.UsersServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        // 2.获取表单和servlet生成的验证码
        String verifycode = request.getParameter("verifycode");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        // 3.校验验证码
        if (!checkcode_server.equalsIgnoreCase(verifycode)) {
            request.setAttribute("login_msg", "验证码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        // 4.验证码校验通过,获取用户名和密码并封装
        Map<String, String[]> login_map = request.getParameterMap();
        Administrator login_administrator = new Administrator();
//        System.out.println(request.getParameter("username"));
        try {
            BeanUtils.populate(login_administrator, login_map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        // 5.校验用户名和密码
        UsersService usersService = new UsersServiceImpl();
        Administrator administrator = usersService.login(login_administrator);
        if (administrator == null) {
            request.setAttribute("login_msg", "用户名或密码输入错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // 6.保存登陆成功的管理员信息到session域
            request.getSession().setAttribute("administrator", administrator);
            /*// 7.获取cookie并保存
            Cookie[] cookies = request.getCookies();
            request.getSession().setAttribute("cookies", cookies);
            // 8.写入cookie
            Cookie cookie = new Cookie("lastVisiti", URLEncoder.encode(new SimpleDateFormat().format(new Date())));
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);*/
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

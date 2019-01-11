package com.hooware.userinfo.web.filter;

import com.hooware.userinfo.domain.Administrator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 1.强转并获得用户请求的URI
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if (uri != null && uri.contains("login") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("checkCode")) {
            // 无需登录,直接放行
            chain.doFilter(req, resp);
        } else {
            Administrator admin = (Administrator) request.getSession().getAttribute("admin");
            if (admin == null) {
                request.setAttribute("login_msg", "亲,您还未登陆,请先登录");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            } else {
                // 已登录,直接放行
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

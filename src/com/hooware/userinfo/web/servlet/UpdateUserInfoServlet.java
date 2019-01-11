package com.hooware.userinfo.web.servlet;

import com.hooware.userinfo.domain.User;
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

/**
 * 修改和更新用户信息
 */
@WebServlet("/updateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置编码
        request.setCharacterEncoding("utf-8");
        // 2.获取表单信息
        Map<String, String[]> updateUserInfoMap = request.getParameterMap();
//        System.out.println(updateUserInfoMap.get("id")[0]);       // 查看用户ID
        // 3.调用Service层进行数据修改(根据ID)
        UserService userService = new UserServiceImpl();
        User user = new User();
        try {
            BeanUtils.populate(user, updateUserInfoMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.updateUserInfoByID(user);
        // 4.重定向到查询界面
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="checkUser.jsp" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>

<c:if test="${empty administrator}">
<span style="font-size: 30px; margin-left: 50%; font-family: 'Leelawadee UI',serif">亲,您还未<a href="${pageContext.request.contextPath}/login.jsp">登录...</a></span>
</c:if>

<c:if test="${not empty administrator}">

<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/findPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${searchMap["name"][0]}" class="form-control" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${searchMap["address"][0]}" class="form-control" id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="email" value="${searchMap["email"][0]}" class="form-control" id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right; margin: 5px">
        <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        <td colspan="8" align="center"><a class="btn btn-primary" href="javascript:deleteSelectedUsers()"
                                          id="delSelected">删除选中</a></td>
    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="headerCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <form action="${pageContext.request.contextPath}/delSelectedUsersServlet" method="post" id="checkBoxForm">
            <c:forEach items="${pb.list}" var="user" varStatus="vs">
                <tr>
                    <td><input type="checkbox" name="tableCb" value="${user.id}"></td>
                    <td>${vs.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm" href="queryUserByIdServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})">删除</a></td>
                </tr>
            </c:forEach>
        </form>

    </table>
<%--   分页部分 --%>
    <div>
        <nav>
            <ul class="pagination">
                <c:if test="${pb.currentPage > 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${searchMap["name"][0]}&address=${searchMap["address"][0]}&email=${searchMap["email"][0]}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <%-- 分页按钮进行左3右2进行划分 --%>
                <c:forEach begin="${(pb.currentPage - 3) > 0 ? (pb.currentPage - 3) : 1}" end="${(pb.currentPage + 2) > pb.pageCount ? pb.pageCount : (pb.currentPage + 2)}" var="i">
                    <%-- 选中页高亮显示 --%>
                    <c:if test="${pb.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/findPageServlet?currentPage=${i}&rows=5&name=${searchMap["name"][0]}&address=${searchMap["address"][0]}&email=${searchMap["email"][0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findPageServlet?currentPage=${i}&rows=5&name=${searchMap["name"][0]}&address=${searchMap["address"][0]}&email=${searchMap["email"][0]}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${pb.currentPage < pb.pageCount}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findPageServlet?currentPage=${pb.currentPage + 1}&rows=5&name=${searchMap["name"][0]}&address=${searchMap["address"][0]}&email=${searchMap["email"][0]}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
            <font style="font-size: 22px; font-family: 'Microsoft JhengHei UI Light',serif; margin-left: 10px;">总共有${pb.totalCount}条数据,共${pb.pageCount}页</font>
        </nav>
    </div>
</div>
</c:if>

<script>
    function deleteUser(id) {
        if (confirm("您确定要删除此条目吗?")) {
            location.href = "${pageContext.request.contextPath}/deleteUserServlet?id=" + id;
        }
    }

    // 全选全不选js函数
    document.getElementById("headerCb").onclick = function () {
        var tableCb = document.getElementsByName("tableCb");
        for (var i = 0; i < tableCb.length; i++) {
            tableCb[i].checked = this.checked;
        }
    }

    function deleteSelectedUsers() {
        var flag = false;
        if (confirm("您确定要删除选中条目吗?")) {
            var tableCb = document.getElementsByName("tableCb");
            for (var i = 0; i < tableCb.length; i++) {
                if (tableCb[i].checked) {
                    flag = true;
                }
            }
            if (flag) {
                document.getElementById("checkBoxForm").submit();
            }
        }
    }


</script>
</body>
</html>

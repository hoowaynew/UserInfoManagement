<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <%--<div style="text-align: center;margin: 6px" class="container-fluid">
        <div style="float: left;">
            <span>姓名:</span><input type="text" placeholder="">
            <span>年龄:</span><input type="text" placeholder="">
            <span>邮箱:</span><input type="text" placeholder="">
            <span><a class="btn btn-primary" href="add.html">查询</a></span>
        </div>
        <div style="float: right">
            <span><a class="btn btn-primary" href="add.html">添加联系人</a></span>
            <span><a class="btn btn-primary" href="add.html">删除选中</a></span>
        </div>
    </div>--%>

    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${searchMap["name"][0]}" class="form-control"
                       id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" value="${searchMap["address"][0]}" class="form-control"
                       id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="email" value="${searchMap["email"][0]}" class="form-control"
                       id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right; margin: 5px">
        <span align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></span>
        <span align="center"><a class="btn btn-primary" href="javascript:void(0)"
                                id="deleteSelected" onclick="deleteSelectedUsers()">删除选中</a></span>
    </div>

    <table border="1" class="table table-bordered table-hover">


        <tr class="success">
            <th><input type="checkbox" id="th_checkbox"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <form action="${pageContext.request.contextPath}/deleteSelectedUserServlet" method="get" id="checkboxForm">
            <c:forEach items="${pb.list}" var="user" varStatus="vs">
                <tr>
                    <td><input type="checkbox" class="td_checkbox" name="id" value="${user.id}"></td>
                    <td>${vs.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/echoUserInfoServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm deleteOneUser" href="javascript:void(0)"
                           onclick="deleteOneUser(${user.id})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </form>

    </table>

    <div>
        <nav>
            <ul class="pagination">
                <li>
                    <c:if test="${pb.currentPage > 1}">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage - 1}&rows=6&name=${searchMap['name'][0]}&address=${searchMap['address'][0]}&email=${searchMap['email'][0]}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </c:if>
                </li>
                <%-- 高亮当前显示页码 --%>
                <c:forEach begin="${(pb.currentPage - 5 > 0) ? ((pb.currentPage + 4 < pb.totalPage) ? pb.currentPage - 5 : pb.totalPage - 10) : 1}" end="${(pb.currentPage + 4 < pb.totalPage) ? (pb.currentPage - 5 > 0 ?  pb.currentPage + 4 : 10) : pb.totalPage}" var="index" varStatus="vs">
                    <c:if test="${index == pb.currentPage}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${index}&rows=6&name=${searchMap['name'][0]}&address=${searchMap['address'][0]}&email=${searchMap['email'][0]}">${index}</a>
                        </li>
                    </c:if>
                    <c:if test="${index != pb.currentPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${index}&rows=6&name=${searchMap['name'][0]}&address=${searchMap['address'][0]}&email=${searchMap['email'][0]}">${index}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <li>
                    <c:if test="${pb.currentPage < pb.totalPage}">
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=6&name=${searchMap['name'][0]}&address=${searchMap['address'][0]}&email=${searchMap['email'][0]}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </c:if>
                </li>
            </ul>
            <span style="margin: 6px; font-size: 20px; vertical-align: 30px">共计${pb.totalCount}条记录,共${pb.totalPage}页,当前第${pb.currentPage}页</span>
        </nav>
    </div>

    <script>
        <%--jquery进行全选全不选函数--%>
        $("#th_checkbox").click(function () {
            $(".td_checkbox").prop("checked", $("#th_checkbox").prop("checked"));
        });

        /*删除某一条目*/
        function deleteOneUser(id) {
            var flag = confirm("您确定要删除此条用户信息吗?");
            if (flag)
                $(".deleteOneUser").attr("href", "${pageContext.request.contextPath}/deleteUserServlet?id=" + id)
        }

        /*获取选中条目的id*/
        function deleteSelectedUsers() {
            // $("#delSelected").click(
            document.getElementById("deleteSelected").onclick = function () {
                var flag = false;
                if (confirm("您确定要删除选中用户信息吗?")) {
                    $(".td_checkbox").each(function () {
                        if (this.checked)
                            flag = true;
                    });
                }
                if (flag) {
                    $("#checkboxForm").submit();
                }
            }
        }
    </script>
</div>
</body>
</html>

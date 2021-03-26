<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    //获取项目名
    String path = request.getContextPath();
    //获取tomcat 协议+地址+端口号+ 获取项目名
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>商品信息</title>
    <link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

    <script type="text/javascript">

        function fenYe(c) {
            //给表单中的隐藏的当前页赋值
            $("#current_page").val(c);
            //提交表单
            $("#bd").submit();
        }

    </script>
</head>
<body>


<div class="rightinfo">


    <div style="height: 45px;background-color:#FAFAD2;border-radius: 7px;line-height: 45px;vertical-align: middle;text-align: center;">
        <!-- 模糊查询 -->
        <form id="bd" action="<%=basePath %>UserCommodityServlet?mark=findCommodityUser" method="post">

            <!-- 隐藏一个当前页数[通过脚本动态赋值] -->
            <input type="hidden" id="current_page" name="current_page" value="">

            商品名称：
            <input type="text" class="dfinput" style="width: 150px" name="name" value="${commodity.name }">

            &nbsp;
            <input type="submit" value="查询" style="width: 80px;height: 35px">
            <input type="button" value="返回首页" style="width: 80px;height: 35px"
                   onclick="location.href='<%=basePath%>IndexServlet?mark=showIndexData'">
        </form>
    </div>
    <table class="tablelist" style="margin-top: 5px">
        <thead>
        <tr>
            <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif"/></i></th>
            <th>商品类别</th>
            <th>商品名称</th>
            <th>商品颜色</th>
            <th>商品价格</th>
            <th width="10%">简介</th>
            <th width="20%">详情</th>
            <th>商品展示图</th>
            <th>是否热推</th>
            <th>型号</th>
            <th>生产日期</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commodityList }" var="comm" varStatus="i">
            <tr>
                <td>${i.count }</td>
                <td>${comm.category.name }</td>
                <td>${comm.name}</td>
                <td>${comm.color}</td>
                <td>${comm.price}</td>
                <td width="10%">${comm.description}</td>
                <td width="20%">${comm.full_description}</td>
                <td>
                    <img width="100px" height="50px" src="http://localhost:8080/${comm.pic }">
                </td>
                <td>
                    <c:if test="${comm.state ==0  }">正常</c:if>
                    <c:if test="${comm.state ==1  }">热门产品</c:if>
                    <c:if test="${comm.state ==2  }">明星单品</c:if>
                    <c:if test="${comm.state ==3  }">促销</c:if>
                </td>
                <td>${comm.version}</td>
                <td>${comm.product_date}</td>

            </tr>


        </c:forEach>

        </tbody>
    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${tools.countRows }</i>条记录，当前显示第&nbsp;<i
                class="blue">&nbsp;${tools.current_page }</i>页
        </div>
        <ul class="paginList">

            <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(1)">首页</a></li>
            <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.pre_page})">上一页</a></li>
            <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.next_page})">下一页</a></li>
            <li class="paginItem"><a href="javascript:void(0)" onclick="fenYe(${tools.count_page})">尾页</a></li>

        </ul>
    </div>
</div>

</body>
</html>

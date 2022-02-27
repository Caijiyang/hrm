<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——用户管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="../css/css.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="../js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <link href="../css/pager.css" type="text/css" rel="stylesheet"/>

    <script type="text/javascript">
        $(function () {
            /** 获取上一次选中的部门数据 */
            var boxs = $("input[type='checkbox'][id^='box_']");

            /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
            $("tr[id^='data_']").hover(function () {
                $(this).css("backgroundColor", "#eeccff");
            }, function () {
                $(this).css("backgroundColor", "#ffffff");
            })


            /** 删除员工绑定点击事件 */
            $("#delete").click(function () {
                /** 获取到用户选中的复选框  */
                var checkedBoxs = boxs.filter(":checked");
                if (checkedBoxs.length < 1) {
                    $.ligerDialog.error("请选择一个需要删除的用户！");
                } else {
                    /** 得到用户选中的所有的需要删除的ids */
                    var ids = checkedBoxs.map(function () {
                        return this.value;
                    })

                    $.ligerDialog.confirm("确认要删除吗?", "删除用户", function (r) {
                        if (r) {
                            // 提交删除的表单
                            $("#del_form").submit();
                            // alert("删除："+ids.get());
                            // 发送请求
                            // window.location = "${ctx }/user/removeUser?ids=" + ids.get();
                        }
                    });
                }
            })
        })

    </script>
</head>
<body>
<!-- 导航 -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理 &gt; 用户查询</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <!-- 查询区  -->
    <tr valign="top">
        <td height="30">
            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                <tr>
                    <td class="fftd">
                        <form action="/user" method="post">
                            <input type="hidden" name="operation" value="selectPage">
                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td class="font3">
                                        用户名：
                                        <input type="text" name="username" value='${requestScope.username==null ? "" : requestScope.username}'>
                                        用户状态：
                                        <select name="status">
                                            <option value="">全部</option>
                                            <option value="1" <c:if test='${requestScope.status eq "1"}'> selected </c:if> >管理员</option>
                                            <option value="2" <c:if test='${requestScope.status eq "2"}'> selected </c:if> >普通用户</option>
                                        </select>
                                        <input type="submit" value="搜索"/>
                                        <input id="delete" type="button" value="删除"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

    <!-- 数据展示区 -->
    <tr valign="top">
        <td height="20">
            <form id="del_form" action="/user" method="post">
                <input type="hidden" name="operation" value="delete">
                <table width="100%" border="1" cellpadding="5" cellspacing="0"
                       style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                    <tbody>
                    <tr class="main_trbg_tit" align="center">
                        <td><input type="checkbox" name="checkAll" id="checkAll"></td>
                        <td>登录名</td>
                        <td>用户名</td>
                        <td>状态</td>
                        <td>创建时间</td>
                        <td align="center">操作</td>
                    </tr>
                    <%--
                        循环显示所有的用户信息
                        items:指定被循环的集合或数组
                        var:每次从集合中获取的元素的名称
                        等价于：for(User user:users){}
                     --%>
                    <c:forEach items="${requestScope.users}" var="user">
                        <tr id="data_0" align="center" class="main_trbg" style="background-color: rgb(255, 255, 255);">
                            <td><input type="checkbox" id="box_0" name="ids" value="${user.id}"></td>
                            <td>${user.loginName}</td>
                            <td>${user.username}</td>
                            <td>
                                    <%-- 由于数据库中存储的是1（管理员）或2（普通用户），所以需要判断 --%>
                                <c:if test='${user.status eq "1"}'>管理员</c:if>
                                <c:if test='${user.status eq "2"}'>普通用户</c:if>
                            </td>
                            <td>${user.createDate}</td>
                            <td align="center" width="40px;">
                                <a href="/user?operation=update&id=${user.id}">
                                    <img title="修改" src="../images/update.gif">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </td>
    </tr>
    <!-- 分页标签 -->
    <tr valign="top">
        <td align="center" class="font3">
            <table width="100%" align="center" style="font-size:13px;" class="digg">
                <tbody>
                <tr>
                    <td style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">
                        <%-- 当前页码大于 1 上一页 按钮才可以使用 --%>
                        <c:if test='${page.pageNum > 1}'>
                            <a href="/user?operation=selectPage&pageNum=${page.pageNum - 1}&username=${requestScope.username}&status=${requestScope.status}">
                                <span>上一页</span>
                            </a>
                        </c:if>
                        <%-- 当前页码小于等于 1 上一页 按钮不可用 --%>
                        <c:if test='${page.pageNum <= 1}'>
                            <span class="disabled">上一页</span>
                        </c:if>

                        <%-- 循环显示所有的页码 --%>
                        <c:forEach begin="1" end="${page.totalPage}" var="index">
                            <c:if test="${page.pageNum == index}">
                                <span class="current">${index}</span>
                            </c:if>
                            <c:if test="${page.pageNum != index}">
                                <a href="/user?operation=selectPage&pageNum=${index}&username=${requestScope.username}&status=${requestScope.status}">
                                    <span>${index}</span>
                                </a>
                            </c:if>
                        </c:forEach>

                        <%-- 当前页码小于总页数（最后一页） 下一页 按钮才可以使用 --%>
                        <c:if test='${page.pageNum < page.totalPage}'>
                            <a href="/user?operation=selectPage&pageNum=${page.pageNum + 1}&username=${requestScope.username}&status=${requestScope.status}">
                                <span>下一页</span>
                            </a>
                        </c:if>
                        <%-- 当前页码大于等于总页数（最后一页） 下一页 按钮不可用用 --%>
                        <c:if test='${page.pageNum >= page.totalPage}'>
                            <span class="disabled">下一页</span>
                        </c:if>
                        <form action="/user" method="post" style="display:inline">
                            <input type="hidden" name="operation" value="selectPage">
                            <input type="hidden" name="username" value="${requestScope.username}">
                            <input type="hidden" name="status" value="${requestScope.status}">
                            跳转到&nbsp;&nbsp;<input style="text-align: center;BORDER-RIGHT: #aaaadd 1px solid; PADDING-RIGHT: 5px; BORDER-TOP: #aaaadd 1px solid; PADDING-LEFT: 5px; PADDING-BOTTOM: 2px; MARGIN: 2px; BORDER-LEFT: #aaaadd 1px solid; COLOR: #000099; PADDING-TOP: 2px; BORDER-BOTTOM: #aaaadd 1px solid; TEXT-DECORATION: none" name="pageNum"  type="text" size="2" id="pager_jump_page_size">&nbsp;
                            <input type="submit" style="text-align: center;BORDER-RIGHT: #dedfde 1px solid; PADDING-RIGHT: 6px; BACKGROUND-POSITION: 50% bottom; BORDER-TOP: #dedfde 1px solid; PADDING-LEFT: 6px; PADDING-BOTTOM: 2px; BORDER-LEFT: #dedfde 1px solid; COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; BORDER-BOTTOM: #dedfde 1px solid; TEXT-DECORATION: none" value="确定" id="pager_jump_btn">
                        </form>

                    </td>
                </tr>
                <tr align="center">
                    <td style="font-size:13px;"></td>
                </tr>
                <tr>
                    <td style="COLOR: #0061de; MARGIN-RIGHT: 3px; PADDING-TOP: 2px; TEXT-DECORATION: none">
                        总共 <font color="red">${page.totalRow}</font>条记录，当前显示${page.startIndex + 1}-${page.pageNum * page.pageRow}条记录。
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>
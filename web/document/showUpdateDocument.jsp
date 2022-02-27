<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——后台管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="../css/css.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="../js/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {

            /** 表单提交的校验 */
            $("#btn").click(function () {
                var title = $("#title").val();
                var file = $("#file").val();

                if ($.trim(title).length <= 2) {
                    alert("请输入两个字符以上的标题");
                    return;
                }

                $("#form1").submit();
            })
        });


    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：文档管理 &gt; 修改文档
        </td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>

            <form id="form1" action="/document" method="post" enctype="multipart/form-data">
                <input type="hidden" name="operation" value="save">
                <input type="hidden" name="id" value="${document.id }">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">
                            文档标题：<input type="text" name="title" size="30" id="title" value="${document.title}"/>
						</td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">文档描述：<br/>
                            <textarea name="remark" cols="88" rows="11" id="content">${document.remark}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">文档：<br/>
                            <input type="file" name="file" id="file" size="30"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">
                            <input type="button" id="btn" value="确定">
                            <input type="reset" value="重置">
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                </table>
            </form>

        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>
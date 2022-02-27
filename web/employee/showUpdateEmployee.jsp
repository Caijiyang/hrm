<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统——修改员工</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="../css/css.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../js/ligerUI/skins/Aqua/css/ligerui-dialog.css">
    <link href="../js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="../js/jquery-migrate-1.2.1.js"></script>
    <script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="../js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
    <link href="../css/pager.css" type="text/css" rel="stylesheet">
    <script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <link href="../js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function submit() {
            // 控制文档加载完成以后 选中性别
            $("#sex").val("0");
            $("#job_id").val("8");

            /** 员工表单提交 */
            //$("#employeeForm").submit(function(){
            alert(1);
            var name = $("#name");
            var cardId = $("#cardId");
            var education = $("#education");
            var email = $("#email");
            var phone = $("#phone");
            var tel = $("#tel");
            var party = $("#party");
            var qqNum = $("#qqNum");
            var address = $("#address");
            var postCode = $("#postCode");
            var birthday = $("#birthday");
            var race = $("#race");
            var speciality = $("#speciality");
            var hobby = $("#hobby");
            var msg = "";
            if ($.trim(name.val()) == "") {
                msg = "姓名不能为空！";
                name.focus();
            } else if ($.trim(cardId.val()) == "") {
                msg = "身份证号码不能为空！";
                cardId.focus();
            } else if (!/^[1-9]\d{16}[0-9A-Za-z]$/.test($.trim(cardId.val()))) {
                msg = "身份证号码格式不正确！";
                cardId.focus();
            } else if ($.trim(education.val()) == "") {
                msg = "学历不能为空！";
                education.focus();
            } else if ($.trim(email.val()) == "") {
                msg = "邮箱不能为空！";
                email.focus();
            } else if (!/^\w+@\w{2,3}\.\w{2,6}$/.test($.trim(email.val()))) {
                msg = "邮箱格式不正确！";
                email.focus();
            } else if ($.trim(phone.val()) == "") {
                msg = "手机号码不能为空！";
                phone.focus();
            } else if (!/^1[3|5|8]\d{9}$/.test($.trim(phone.val()))) {
                msg = "手机号码格式不正确！";
                phone.focus();
            } else if ($.trim(tel.val()) == "") {
                msg = "电话号码不能为空！";
                tel.focus();
            } else if (!/^0\d{2,3}-?\d{7,8}$/.test($.trim(tel.val()))) {
                msg = "电话号码格式不正确！";
                tel.focus();
            } else if ($.trim(party.val()) == "") {
                msg = "政治面貌不能为空！";
                party.focus();
            } else if ($.trim(qqNum.val()) == "") {
                msg = "QQ号码不能为空！";
                qqNum.focus();
            } else if (!/^\d{6,}$/.test($.trim(qqNum.val()))) {
                msg = "QQ号码格式不正确！";
                qqNum.focus();
            } else if ($.trim(address.val()) == "") {
                msg = "地址不能为空！";
                address.focus();
            } else if ($.trim(postCode.val()) == "") {
                msg = "邮政编码不能为空！";
                postCode.focus();
            } else if (!/^[1-9]\d{5}$/.test($.trim(postCode.val()))) {
                msg = "邮政编码格式不正确！";
                postCode.focus();
            } else if ($.trim(birthday.val()) == "") {
                msg = "出生日期不能为空！";
                birthday.focus();
            } else if (!birthday.val()) {
//					!/^\d{4}-\d{2}-\d{2}$/.test($.trim(birthday.val()))
                msg = "出生日期格式不正确！";
                birthday.focus();
            } else if ($.trim(race.val()) == "") {
                msg = "民族不能为空！";
                race.focus();
            } else if ($.trim(speciality.val()) == "") {
                msg = "专业不能为空！";
                speciality.focus();
            } else if ($.trim(hobby.val()) == "") {
                msg = "爱好不能为空！";
                hobby.focus();
            }
            if (msg != "") {
                $.ligerDialog.error(msg);
                return false;
            } else {
                $("#form1").submit();
                return true;
            }
            //
            //});
        }


    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tbody>
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="../images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="../images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 修改员工</td>
        <td width="15" height="32"><img src="../images/main_locright.gif" width="15" height="32"></td>
    </tr>
    </tbody>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tbody>
    <tr valign="top">
        <td>

            <table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
                <tbody>
                <tr valign="top">
                    <td>

                        <form id="form1" action="/employee" method="post">
                            <input type="hidden" name="operation" value="save">
                            <input type="hidden" name="id" value="${employee.id}">
                            <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                                <tbody>
                                <tr>
                                    <td class="font3 fftd">
                                        <table>
                                            <tbody>
                                            <tr>
                                                <td class="font3 fftd">
                                                    姓名：<input type="text" name="name" id="name" size="20" value="${employee.name}">
                                                </td>
                                                <td class="font3 fftd">
                                                    身份证号码：<input type="text" name="cardid" id="cardId" size="20"
                                                                 value="${employee.cardId}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="font3 fftd">性别：
                                                    <select id="sex" name="sex" style="width:143px;">
                                                        <option value="1" <c:if test='${employee.sex eq "1"}'> selected </c:if> >男</option>
                                                        <option value="2" <c:if test='${employee.sex eq "2"}'> selected </c:if> >女</option>
                                                    </select>
                                                </td>
                                                <td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
                                                    <select id="job_id" name="jobid" style="width:143px;">
                                                        <c:forEach items="${requestScope.jobs }" var="job">
                                                            <option value="${job.id }" <c:if test='${job.id eq employee.jobId}'> selected </c:if> >${job.name }</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="font3 fftd">
                                                    学历：<input name="education" id="education" size="20" value="${employee.education}">
                                                </td>
                                                <td class="font3 fftd">
                                                    邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input name="email" id="email" size="20" value="${employee.email}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="font3 fftd">
                                                    手机：<input name="phone" id="phone" size="20" value="${employee.phone}">
                                                </td>
                                                <td class="font3 fftd">
                                                    电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：<input name="tel" id="tel" size="20" value="${employee.tel}">
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td class="font3 fftd">
                                        政治面貌：<input name="party" id="party" size="40" value="${employee.party}">&nbsp;&nbsp;
                                        QQ&nbsp;&nbsp;号码：<input name="qqnum" id="qqNum" size="20" value="${employee.qqNum}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td class="font3 fftd">
                                        联系地址：<input name="address" id="address" size="40" value="${employee.address}">&nbsp;&nbsp;
                                        邮政编码：<input name="postcode" id="postCode" size="20" value="${employee.postCode}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td class="font3 fftd">
                                        出生日期：<input cssclass="Wdate" value="${employee.birthday}"
                                                    onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"
                                                    name="birthday" id="birthday" size="40">&nbsp;&nbsp;
                                        民&nbsp;&nbsp;&nbsp;&nbsp;族：<input name="race" id="race" size="20" value="${employee.race}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td class="font3 fftd">
                                        所学专业：<input name="speciality" id="speciality" size="40" value="${employee.speciality}">&nbsp;&nbsp;
                                        爱&nbsp;&nbsp;&nbsp;&nbsp;好：<input name="hobby" id="hobby" size="20" value="${employee.hobby}">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td class="font3 fftd">
                                        备&nbsp;&nbsp;&nbsp;&nbsp;注：<input name="remark" id="remark" size="40" value="${employee.remark}">
                                        所属部门：
                                        <select name="depid" style="width:100px;">
                                            <c:forEach items="${requestScope.depts }" var="dept">
                                                <option value="${dept.id }" <c:if test='${dept.id eq employee.depId}'> selected </c:if> >${dept.name }</option>
                                            </c:forEach>
                                        </select>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="font3 fftd">
                                        所属薪资范围：
                                        <select name="levelid" style="width:300px;">
                                            <c:forEach items="${requestScope.levels }" var="level">
                                                <option value="${level.id }" <c:if test='${employee.levelId == level.id}'> selected </c:if> >${level.range }</option>
                                            </c:forEach>
                                        </select>
                                        &nbsp;&nbsp;薪&nbsp;&nbsp;&nbsp;&nbsp;资：<input name="salary" value="${employee.salary}" id="salary" size="20">
                                    </td>
                                </tr>

                                <tr>
                                    <td class="main_tdbor"></td>
                                </tr>

                                <tr>
                                    <td align="left" class="fftd">
                                        <input type="button" onclick="submit()" value="修改">
                                        <input type="reset" value="取消 ">
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>

                    </td>
                </tr>
                </tbody>
            </table>

        </td>
    </tr>
    </tbody>
</table>
<div style="height:10px;"></div>

</body>
</html>
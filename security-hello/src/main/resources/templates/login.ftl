<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h1>Login Page</h1>
    <form action="${base}/toLogin" method="POST"> <#-- 只支持post方式登录 -->
        <#--<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>-->
        <input type="text" name="user" /><br>
        <input type="password" name="pwd" /><br>
        <input type="submit"  value="登录">
    </form>
</body>
</html>
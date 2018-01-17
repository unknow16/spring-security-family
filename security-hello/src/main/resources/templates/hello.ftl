<#assign base = request.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <h1>Hello page</h1>
    <form action="${base}/logout" method="post">
        <input type="submit" value="注销">
        <#--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->
    </form>
</body>
</html>
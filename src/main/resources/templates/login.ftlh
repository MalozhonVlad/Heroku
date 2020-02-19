<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="/css/login.css">

</head>
<body>

<div class="loginbox">
    <img src="/css/avatar1.png" class="avatar">
    <h1>Login Here</h1>

    <form action="/login" method="post">
        <#if code??>
            <h3 class="successfullyActivated">${code}</h3>
        </#if>
<#--        <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>-->
<#--            <h3 class="bad">${SPRING_SECURITY_LAST_EXCEPTION.message}</h3>-->
<#--        </#if>-->
        <p>Username</p>
        <input type="text" name="username" placeholder="Enter Username">

        <p>Password</p>
        <input type="password" name="password" placeholder="Enter Password">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <input type="submit" value="Sign In">
<#--        <a href="#">Lost your password?</a><br>-->

        <a href="registration">Don't have an account?</a>

    </form>

</div>



</body>
</html>
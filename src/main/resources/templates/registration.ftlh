<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="css/registration.css">

    <link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">

</head>
<body>


<div class="signup-form">
    <form action="/registration" method="post">
        <h1>Registration</h1>
            <#if message??>
                <h2>${message}</h2>
            </#if>

            <#if  usernameError??>
                <h3>${usernameError}</h3>
            </#if>
        <input type="text" value="<#if user??>${user.username}</#if>" name="username" placeholder="Username" class="txtb" >
            <#if  passwordError??>
                <h3>${passwordError}</h3>
            </#if>
            <#if differentPassword??>
                <h3>${differentPassword}</h3>
            </#if>
        <input type="password" name="password" placeholder="Password" class="txtb">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <#if  passwordError??>
            <h3>${passwordError}</h3>
        </#if>
<#--        <#if differentPassword??>-->
<#--            <h3>${differentPassword}</h3>-->
<#--        </#if>-->
        <input type="password" name="password2" placeholder="Repeat Password " class="txtb">

            <#if  emailError??>
                <h3>${emailError}</h3>
            </#if>
        <input type="email" value="<#if user??>${user.email}</#if>" name="email" placeholder="Email" class="txtb">

        <input type="submit" value="Create Account" class="signup-btn">

    </form>
</div>



</body>
</html>
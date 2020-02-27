<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Sider Menu Bar CSS</title>
    <link rel="stylesheet" href="/css/greeting.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
<input type="checkbox" id="check">
<label for="check">
    <i class="fas fa-bars" id="btn"></i>
    <i class="fas fa-times" id="cancel"></i>
</label>
<div class="sidebar">
    <header>Welcome <#if user??>${user.username}</#if> </header>
    <ul>
        <li><a href="/helloSweaterPage"><i class="fas fa-qrcode"></i>Messenger</a></li>
<#--        <li><a href="#"><i class="fas fa-link"></i>Project in Progress.....</a></li>-->
<#--        <li><a href="#"><i class="fas fa-stream"></i>Overview</a></li>-->
<#--        <li><a href="#"><i class="fas fa-calendar-week"></i>Events</a></li>-->
<#--        <li><a href="/aboutProject"><i class="far fa-question-circle"></i>About</a></li>-->
<#--        <li><a href="#"><i class="fas fa-sliders-h"></i>Services</a></li>-->
<#--        <li><a href="/resume"><i class="far fa-envelope"></i>Contact</a></li>-->



<#--        <l1><i><form action="/logout" method="post">-->
<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--            <button type="submit">Sign Out</button>-->
<#--        </form></l1></i>-->
    </ul>
</div>
<section></section>

</body>
</html>

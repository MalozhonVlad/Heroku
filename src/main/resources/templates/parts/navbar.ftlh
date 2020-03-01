<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Sweater</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/helloSweaterPage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/messages">Messages</a>
            </li>
            <#--            <#if isAdmin>-->
            <#--                <li class="nav-item">-->
            <#--                    <a class="nav-link" href="/messages">зделать страничку пользователей видно только админу!!!</a>-->
            <#--                </li>-->
            <#--            </#if>-->

            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/myMessages">My Messages</a>
                </li>
            </#if>

        </ul>

        <div class="navbar-text mr-3">${name}</div>

        <#if user??>
            <div>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary" type="submit">Sign Out</button>
                </form>
            </div>
        <#else>
            <div>
                <form action="/myMessages" method="get">
<#--                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>-->
                    <button class="btn btn-primary" type="submit">Sign in</button>
                </form>
            </div>
        </#if>

    </div>
</nav>
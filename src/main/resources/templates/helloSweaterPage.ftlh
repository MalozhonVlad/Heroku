<#import "parts/common.ftlh" as c>

<#--<#include "parts/navbar.ftlh">-->
<@c.page>
    <h5>Hello <#if user??>${user.username}</#if></h5>
    <div>This is simple clone of Twitter</div>
</@c.page>


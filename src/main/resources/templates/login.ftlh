<#--<#include "parts/security.ftlh">-->
<#import "parts/common.ftlh" as c>


<@c.page>

    <br>
    <br>
    <br>
    <br>
    <br>

<#--    <#if !Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>-->
    <h2>Login page</h2>
<#--    </#if>-->
    <div class="form-group mt-3">
        <form action="/login" method="post">

            <#if code??>
                <h3 class="successfullyActivated">${code}</h3>
            </#if>
<#--            <div class="invalid-feedback d-block">-->
<#--                <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>-->
<#--                    <h3 class="bad">${SPRING_SECURITY_LAST_EXCEPTION.message}</h3>-->
<#--                </#if>-->
<#--            </div>-->
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">User Name :</label>
                <div class="col-sm-8">
                    <input type="text" name="username" placeholder="Username" class="form-control">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password :</label>
                <div class="col-sm-8">
                    <input type="password" name="password" placeholder="Password" class="form-control">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group mt-2">
                <button type="submit" class="btn btn-primary">Sign In</button>
            </div>

            <div>
                <a href="registration">Don't have an account?</a>
            </div>

        </form>
    </div>

</@c.page>
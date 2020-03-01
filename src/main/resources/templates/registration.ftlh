
<#import "parts/common.ftlh" as c>


<@c.page>

    <div class="form-group mt-3">
        <form action="/registration" method="post">
            <h3>Registration</h3>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">User Name :</label>
                <div class="col-sm-8">
                    <input type="text" value="<#if user??>${user.username}</#if>" name="username" placeholder="Username"
                           class="form-control">
                    <#if message??>
                        <h2>${message}</h2>
                    </#if>
                    <#if  usernameError??>
                        <div class="invalid-feedback d-block">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password :</label>
                <div class="col-sm-8">
                    <input type="password" name="password" placeholder="Password" class="form-control">
                    <#if  passwordError??>
                        <div class="invalid-feedback d-block">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Repeat Password :</label>
                <div class="col-sm-8">
                    <input type="password" name="password2" placeholder="Repeat Password " class="form-control">
                    <#if  passwordError??>
                        <div class="invalid-feedback d-block">
                            ${passwordError}
                        </div>
                    </#if>
                    <#if differentPassword??>
                        <div class="invalid-feedback d-block">
                            ${differentPassword}
                        </div>
                    </#if>
                </div>
            </div>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email :</label>
                <div class="col-sm-8">
                    <input type="email" value="<#if user??>${user.email}</#if>" name="email" placeholder="Email"
                           class="form-control">
                    <#if  emailError??>
                        <div class="invalid-feedback d-block">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group mt-2">
                <button type="submit" class="btn btn-primary">Create Account</button>
            </div>

        </form>
    </div>


</@c.page>
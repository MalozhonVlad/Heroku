<#--<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"-->
<#--   aria-controls="collapseExample">-->
<#--    Message Editor-->
<#--</a>-->

<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text" name="text" class="form-control"
                       value="<#if message??>${message.text}</#if>" placeholder="Введите сообщение">
                <#if textError??>
                    <div class="invalid-feedback d-block">
                        ${textError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text" name="tag" value="<#if message??>${message.tag}</#if>"
                       class="form-control" placeholder="Тэг">
                <#if tagError??>
                    <div class="invalid-feedback d-block">
                        ${tagError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>

            <div class="form-group mt-2">
                <button type="submit" class="btn btn-primary">Save message</button>
            </div>
        </form>

        <form method="get" action="/deleteMessage/<#if message??>${message.id}</#if>">
            <div class="form-group mt-2">
                <button type="submit" class="btn btn-danger">Delete Message</button>
            </div>
        </form>

    </div>
</div>



<div class="card-columns">
    <div class="card my-3">
        <#if message.filename??>
            <img src="${message.filename}" class="card-img-top">
        </#if>
        <div class="m-2">
            <span>${message.text}</span><br>
            <i>#${message.tag}</i>
        </div>
        <div class="card-footer text-muted">
            <a href="/user-messages/${message.author.id}">${message.author.username}</a>
            <#--        <#if message.author.id == currentUserId>-->
            <#--            <a class="btn btn-primary" href="/editMessage/${message.author.id}?message=${message.id}">-->
            <#--                Edit-->
            <#--            </a>-->
            <#--        </#if>-->
        </div>
    </div>
</div>
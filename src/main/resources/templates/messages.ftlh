<#import "parts/common.ftlh" as c>


<@c.page>

    <div class="form-group mt-3">
        <form action="/filter" method="post">
            <input type="text" name="filter" placeholder="Find by Tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Найти</button>
        </form>
    </div>


    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Message
    </a>


    <div class="collapse <#if message??>show</#if>" id="collapseExample">
        <div class="form-group mt-3">
            <form action="/messages" method="post" enctype="multipart/form-data">

                <div class="form-group">
                    <input type="text" name="text" class="form-control" placeholder="Введите сообщение">
                    <#if textError??>
                        <div class="invalid-feedback d-block">
                            ${textError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" name="tag" class="form-control" placeholder="Тэг">
                    <#if tagError??>
                        <div class="invalid-feedback d-block">
                            ${tagError}
                        </div>
                    </#if>
                </div>
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group mt-2">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>



    <#include "parts/messageList.ftlh" />

</@c.page>
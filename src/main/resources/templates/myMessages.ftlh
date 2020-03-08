<#import "parts/common.ftlh" as c>
<#--<#include "parts/security.ftlh">-->


<@c.page>
    <#if authorOfMessages??>
        <div>
            <h3>${authorOfMessages.username}</h3>
        </div>
    <#else>
        <div>
            <h3>${user.username}</h3>
        </div>
    </#if>

    <#if authorId??>
        <#if isSubscriber>
            <#if authorId != user.id>
                <a class="btn btn-info" href="/user/unsubscribe/${user.id}">Unsubscribe</a>
            </#if>
        <#else >
            <a class="btn btn-info" href="/user/subscribe/${user.id}">Subscribe</a>
        </#if>
    </#if>


    <div class="container my-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <#--                            <a href="/user/subscriptions/${authorOfMessages.id}">${subscriptionsCount}</a>-->
                            <a href="/user/subscriptions/${user.id}">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <#--                            <a href="/user/subscribers/${authorOfMessages.id}">${subscribersCount}</a>-->
                            <a href="/user/subscribers/${user.id}">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#if delete??>
        <div class="invalid-feedback d-block">
            <h5>${delete}</h5>
        </div>
    </#if>


    <#include "parts/messageList.ftlh" />

</@c.page>

<#import "parts/common.ftlh" as c>


<@c.page>

    <#if delete??>
        <div class="invalid-feedback d-block">
            <h5>${delete}</h5>
        </div>
    </#if>
    <#include "parts/messageList.ftlh" />

</@c.page>

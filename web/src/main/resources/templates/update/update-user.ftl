<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.update-user"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/uui/css/uui-all.css"/>
    <link rel="stylesheet" href="/uui/css/create-form.css"/>
    <link rel="stylesheet" href="/uui/fonts/font-awesome/css/font-awesome.min.css"/>
</head>
<body>
<div class="wrapper">
    <#include "../template/header.ftl">
    <div class="uui-main-container site-main-container"
         style="margin-left: 200px; margin-right: 200px; margin-top: 50px;">
        <main>
            <div class="main-content">
                <div class="row">
                    <div class="panel text-center">
                        <form action="/update-user" method="post">
                            <h1 class="section-title"><@spring.message code="title.update-user"/></h1>
                            <input type="hidden" name="id" value="${user.id?c}">
                            <div>
                                <label for="login" class="uui-big-text">Login: <span class="red">*</span></label>
                                <input type="text" id="login" name="login" placeholder="login" class="uui-form-element"
                                       value="${user.login}" pattern=".{3,20}" required title="3 to 20 characters"/>

                            </div>
                            <br>
                            <div>
                                <label for="password" class="uui-big-text">Password: <span class="red">*</span></label>
                                <input type="text" id="password" name="password" placeholder="password"
                                       class="uui-form-element" value="${user.password}" pattern=".{3,40}" required
                                       title="3 to 40 characters"/>
                            </div>
                            <br>
                            <div>
                                <label for="userRole" class="uui-big-text">Role: <span class="red">*</span></label>
                                <select id="userRole" name="userRole" class="selectpicker uui-form-element">
                                <#list userRoles as role>
                                    <option value="${role}" <#if role==user.userRole>selected</#if>>${role}</option>
                                </#list>
                                </select>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Update</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>


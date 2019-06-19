<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.register"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/uui/css/uui-all.css"/>
    <link rel="stylesheet" href="/uui/fonts/font-awesome/css/font-awesome.min.css"/>
</head>
<body>
<div class="wrapper">
    <#include "../template/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title"><@spring.message code="title.register"/></h1>
                        <form role="form" action="/register-user" method="post">
                            <div>
                                <label for="login" class="uui-big-text text-uppercase">Login</label>
                                <br>
                                <input name="login" type="text" placeholder="login" class="uui-form-element large"
                                       pattern=".{3,20}" required title="3 to 20 characters" autofocus/>
                            </div>
                            <br>
                            <div>
                                <label for="password" class="uui-big-text text-uppercase">Password</label>
                                <br>
                                <input name="password" type="password" placeholder="password"
                                       class="uui-form-element large" pattern=".{3,40}" required
                                       title="3 to 40 characters"/>
                            </div>
                            <br>
                            <button type="submit" class="uui-button large blue">Register</button>
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

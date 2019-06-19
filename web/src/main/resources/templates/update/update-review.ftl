<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.update-review"/></title>
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
                        <form action="/update-review" method="post">
                            <h1 class="section-title"><@spring.message code="title.update-review"/></h1>
                            <input type="hidden" name="id" value="${review.id?c}">
                            <input type="hidden" name="user" value="${review.user.id}">
                            <input type="hidden" name="tour" value="${review.tour.id}">
                            <input type="hidden" name="date" value="${review.date}">
                            <div>
                                <label for="text" class="uui-big-text">Review text: <span class="red">*</span></label>
                                <input type="text" id="text" name="text" placeholder="text" class="uui-form-element"
                                       value="${review.text}" required/>
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


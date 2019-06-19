<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.review"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/uui/css/uui-all.css"/>
    <link rel="stylesheet" href="/uui/fonts/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/uui/css/custom.css"/>
</head>
<body>
<div class="wrapper">
    <#include "../template/header.ftl">
    <div class="uui-main-container site-main-container"
         style="margin-left: 200px; margin-right: 200px; margin-top: 50px;">
        <main>
            <div class="main-content">
                <div class="row">
                    <div class="panel text-left">
                        <h1 class="section-title"><@spring.message code="title.review"/></h1>
                        <#if isAdmin>
                            <form action="/update-review/${review.id?c}" method="get"
                                  style="display: inline">
                                <button type="submit" class="uui-button orange small">Update</button>
                            </form>
                            <form action="/delete-review" method="post" style="display: inline">
                                <input type="hidden" name="reviewId" value="${review.id?c}">
                                <button type="submit"
                                        onclick="confirm('Confirm deleting review')"
                                        class="uui-button raspberry small">Delete
                                </button>
                            </form>
                        </#if>
                        <div>
                            <div class="headline">
                                <h1>Date: </h1>
                                <p>${review.date?date?string.medium}</p>
                            </div>
                            <div class="headline">
                                <h1>Text: </h1>
                                <p>${review.text}</p>
                            </div>
                            <div class="headline">
                                <h1>User: </h1>
                                <p><a href="/user/${review.user.id}">${review.user.login}</a></p>
                            </div>
                            <div class="headline">
                                <h1>Tour: </h1>
                                <p><a href="/tour/${review.tour.id}">${review.tour.description}</a></p>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>

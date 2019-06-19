<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.profile"/></title>
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
        <main>
            <div class="uui-person-vertical-profile dark-gray">
                <div class="person-profile-body">
                    <div class="person-profile-section">
                        <div class="person-profile-info">
                            <h2>${user.login}</h2>
                            <p>${user.userRole}</p>
                        </div>
                        <div class="person-profile-photo">
                            <img src="/uui/images/icons/no_photo.png" alt=""/>
                        </div>
                    </div>
                </div>
                <#if isAdmin>
                    <td>
                        <form action="/update-user/${user.id?c}" method="get"
                              style="display: inline">
                            <button type="submit" class="uui-button orange small">Update</button>
                        </form>
                        <form action="/delete-user" method="post" style="display: inline">
                            <input type="hidden" name="userId" value="${user.id?c}">
                            <button type="submit"
                                    onclick="confirm('Confirm deleting user')"
                                    class="uui-button raspberry small">Delete
                            </button>
                        </form>
                    </td>
                </#if>
                <div class="person-profile-footer">
                    <div class="person-profile-section">
                        <#if tours?has_content>
                                <h1>Tours:</h1>
                                <ul>
                                    <#list tours as tour>
                                        <li type="none">
                                            ${tour.country.name} ${tour.date?date?string.medium}
                                        </li>
                                    </#list>
                                </ul>
                        </#if>
                        <#if reviews?has_content>
                            <h1>Reviews:</h1>
                            <ul>
                                <#list reviews as review>
                                    <li type="none">
                                        ${review.tour.country.name} ${review.tour.tourType} ${review.date?date?string.medium}
                                        <br>
                                        ${review.text}
                                        <br>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>

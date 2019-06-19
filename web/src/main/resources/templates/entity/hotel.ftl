<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.hotel"/></title>
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
                        <h1 class="section-title"><@spring.message code="title.hotel"/></h1>
                        <#if isAdmin>
                            <td>
                                <form action="/update-hotel/${hotel.id?c}" method="get"
                                      style="display: inline">
                                    <button type="submit" class="uui-button orange small">Update
                                    </button>
                                </form>
                                <form action="/delete-hotel" method="post" style="display: inline">
                                    <input type="hidden" name="hotelId" value="${hotel.id?c}">
                                    <button type="submit"
                                            onclick="confirm('Confirm deleting hotel')"
                                            class="uui-button raspberry small">Delete
                                    </button>
                                </form>
                            </td>
                        </#if>
                        <div>
                            <div class="headline">
                                <h1>Name: </h1>
                                <p>${hotel.name}</p>
                            </div>
                            <div class="headline">
                                <h1>Stars: </h1>
                                <p>${hotel.stars?c}</p>
                            </div>
                            <div class="headline">
                                <h1>Website: </h1>
                                <p>${hotel.website} days</p>
                            </div>
                            <div class="headline">
                                <h1>Latitude: </h1>
                                <p>${hotel.latitude}</p>
                            </div>
                            <div class="headline">
                                <h1>Longitude: </h1>
                                <p>${hotel.longitude}</p>
                            </div>
                            <div class="headline">
                                <h1>Features: </h1>
                                <p>
                                    <#list hotel.features as feature>
                                        <#if feature?has_next>
                                            ${feature},
                                        <#else>
                                            ${feature}
                                        </#if>
                                    </#list>
                                </p>
                            </div>
                        </div>
                        <br>
                        <#if tours?has_content>
                                Tours:
                            <#list tours as tour>
                                <article>
                                    <a href="/tour/${tour.id?c}">${tour.description}</a> ${tour.date?date?string.medium}
                                </article>
                            </#list>
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

<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.tour"/></title>
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
                        <h1 class="section-title"><@spring.message code="title.tour"/></h1>
                        <#if isAdmin>
                            <form action="/update-tour/${tour.id?c}" method="get"
                                  style="display: inline">
                                <button type="submit" class="uui-button orange small">Update</button>
                            </form>
                            <form action="/delete-tour" method="post" style="display: inline">
                                <input type="hidden" name="tourId" value="${tour.id?c}">
                                <button type="submit"
                                        onclick="confirm('Confirm deleting tour')"
                                        class="uui-button raspberry small">Delete
                                </button>
                            </form>
                        </#if>
                        <br>
                        <div>
                            <div class="headline">
                                <h1>Country: </h1>
                                <p>${tour.country.name}</p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Date: </h1>
                                <p>${tour.date?date?string.medium}</p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Duration: </h1>
                                <p>${tour.duration} days</p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Photo: </h1>
                                <p><img src="${tour.photo}"></p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Hotel: </h1>
                                <p><a href="/hotel/${tour.hotel.id?c}">${tour.hotel.name}</a>, ${tour.hotel.stars} stars
                                </p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Description: </h1>
                                <p>${tour.description}</p>
                            </div>
                            <br>
                            <div class="headline">
                                <h1>Cost: </h1>
                                <p>$${tour.cost}</p>
                            </div>
                        </div>
                        <#if isAuthenticated>
                            <#if !isFavorite>
                            <form action="/tour/${tour.id?c}" method="post">
                                <input type="hidden" name="add" value="true">
                                <button type="submit"
                                        class="uui-button lime-green"><@spring.message code="title.add-to-favourite"/></i></button>
                            </form>
                            <#else>
                            <form action="/tour/${tour.id?c}" method="post">
                                <input type="hidden" name="add" value="false">
                                <button type="submit"
                                        class="uui-button raspberry"><@spring.message code="title.delete-from-favourite"/></i></button>
                            </form>
                            </#if>
                        </#if>
                        <br>
                        Reviews:
                        <#list reviews as review>
                            <article>
                                <a href="/user/${review.user.id?c}">${review.user.login}</a> ${review.date?date?string.medium}
                                : ${review.text}
                                <#if review.user.id==currentUserId>
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
                            </article>
                        </#list>
                        <br>
                        <form action="/create-review/${tour.id?c}" method="get">
                            <button type="submit"
                                    class="uui-button blue"><@spring.message code="title.add-review"/></i></button>
                        </form>
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

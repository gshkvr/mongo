<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.update-hotel"/></title>
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
                        <form action="/update-hotel" method="post">
                            <h1 class="section-title"><@spring.message code="title.update-hotel"/></h1>
                            <input type="hidden" name="id" value="${hotel.id?c}">
                            <div>
                                <label for="name" class="uui-big-text">Hotel name: <span class="red">*</span></label>
                                <input type="text" id="name" name="name" placeholder="name" class="uui-form-element"
                                       value="${hotel.name}" pattern=".{3,40}" required title="3 to 40 characters"/>
                            </div>
                            <br>
                            <div>
                                <label for="stars" class="uui-big-text">Stars: <span class="red">*</span></label>
                                <input type="number" min="1" max="5" id="stars" name="stars" placeholder="stars"
                                       value="${hotel.stars}" class="uui-form-element"/>
                            </div>
                            <br>
                            <div>
                                <label for="website" class="uui-big-text">Website: <span class="red">*</span></label>
                                <input type="text" id="website" name="website" placeholder="website"
                                       value="${hotel.website}" class="uui-form-element" pattern=".{3,40}" required
                                       title="3 to 40 characters"/>
                            </div>
                            <br>
                            <div><label for="latitude" class="uui-big-text">Latitude: <span class="red">*</span></label>
                                <input type="text" id="latitude" name="latitude" placeholder="latitude"
                                       value="${hotel.latitude}" class="uui-form-element" pattern=".{3,40}" required
                                       title="3 to 40 characters"/>
                            </div>
                            <br>
                            <div>
                                <label for="longitude" class="uui-big-text">Longitude: <span class="red">*</span></label>
                                <input type="text" id="longitude" name="longitude" placeholder="longitude"
                                       value="${hotel.longitude}" class="uui-form-element" pattern=".{3,40}" required
                                       title="3 to 40 characters"/>
                            </div>
                            <br>
                            <div>
                                <label for="features" class="uui-big-text">Features: </label>
                                <div class="uui-input-group vertical">
                                    <p class="uui-checkbox">
                                        <#list features as feature>
                                            <input type="checkbox" id="${feature}" name="features" value="${feature}"
                                                <#list hotel.features as sFeature>
                                                    <#if sFeature==feature>
                                                        checked
                                                    </#if>
                                                </#list>/>
                                            <label for="${feature}">${feature}</label>
                                        </#list>
                                    </p>
                                </div>
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


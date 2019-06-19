<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.update-tour"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/uui/css/uui-all.css"/>
    <link rel="stylesheet" href="/uui/css/create-form.css"/>
    <link rel="stylesheet" href="/uui/fonts/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/uui/css/lib/components/datepicker3.css"/>
    <script src="/uui/js/lib/components/bootstrap-datepicker.js"></script>
    <script src="/uui/js/uui-datepicker.min.js"></script>
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
                        <form action="/update-tour" method="post">
                            <h1 class="section-title"><@spring.message code="title.update-tour"/></h1>
                            <input type="hidden" name="id" value="${tour.id?c}">
                            <div>
                                <label for="date" class="uui-big-text">Date: <span class="red">*</span></label>
                                <div class="date uui-datepicker date-button" style="width:220px">
                                    <input type="text" id="datepicker" name="date" class="uui-form-element"
                                           placeholder="mm/dd/yyyy" required/>
                                </div>
                                <script>
                                    $('#datepicker').uui_datepicker({todayHighlight: true});
                                </script>
                            </div>
                            <br>
                            <div>
                                <label for="country" class="uui-big-text">Country: <span class="red">*</span></label>
                                <select class="selectpicker uui-form-element" id="country" name="country">
                                    <#list countries as country>
                                        <option value="${country.id}"
                                        <#if country.id == tour.country.id>
                                            selected
                                        </#if>>
                                            ${country.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <br>
                            <div>
                                <label for="duration" class="uui-big-text">Duration: <span class="red">*</span></label>
                                <input type="number" id="duration" name="duration" placeholder="duration"
                                       class="uui-form-element" value="${tour.duration}" required/>
                            </div>
                            <br>
                            <div>
                                <label for="description" class="uui-big-text">Description: <span class="red">*</span></label>
                                <input type="text" id="description" name="description" placeholder="description"
                                       class="uui-form-element" value="${tour.description}" required/>
                            </div>
                            <br>
                            <div>
                                <label for="hotel" class="uui-big-text">Hotel: <span class="red">*</span></label>
                                <select class="selectpicker uui-form-element" id="hotel" name="hotel">
                                    <#list hotels as hotel>
                                        <option value="${hotel.id}"
                                        <#if hotel.id==tour.hotel.id>
                                            selected
                                        </#if>>${hotel.name}</option>
                                    </#list>
                                </select>
                            </div>
                            <br>
                            <div>
                                <label for="photo" class="uui-big-text">Photo: <span class="red">*</span></label>
                                <input type="text" id="photo" name="photo" placeholder="photo" class="uui-form-element"
                                       value="${tour.photo}" required/>

                            </div>
                            <br>
                            <div>
                                <label for="tourType" class="uui-big-text">Tour type: <span class="red">*</span></label>
                                <select class="selectpicker uui-form-element" id="tourType" name="tourType">
                                    <#list tourTypes as type>
                                        <option value="${type}"
                                        <#if type==tour.tourType.toString()>
                                            selected
                                        </#if>>${type}</option>
                                    </#list>
                                </select>
                            </div>
                            <br>
                            <div>
                                <label for="cost" class="uui-big-text">Cost: <span class="red">*</span></label>
                                <input type="number" id="cost" name="cost" placeholder="cost" class="uui-form-element"
                                       value="${tour.cost}" required/>
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


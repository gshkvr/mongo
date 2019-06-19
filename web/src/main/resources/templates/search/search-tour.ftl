<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.search-tour"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/jquery-ui/search.js"></script>
    <script src="/uui/jquery-ui/jquery-dateformat.min.js"></script>
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
                        <h1 class="section-title"><@spring.message code="title.search-tour"/></h1>
                        <div>
                            <label for="country" class="uui-big-text">Country: </label>
                            <select class="selectpicker uui-form-element" id="country" name="country">
                                <option value=""></option>
                                <#list countries as country>
                                    <option value="${country.id}">${country.name}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <div>
                            <label for="duration" class="uui-big-text">Duration: </label>
                            <input type="number" id="duration" name="duration" placeholder="duration"
                                   class="uui-form-element"/>
                        </div>
                        <br>
                        <div>
                            <label for="hotel" class="uui-big-text">Hotel: </label>
                            <select class="selectpicker uui-form-element" id="hotel" name="hotel">
                                <option value=""></option>
                                <#list hotels as hotel>
                                    <option value="${hotel.id}">${hotel.name}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <div>
                            <label for="tourType" class="uui-big-text">Tour type: </label>
                            <select class="selectpicker uui-form-element" id="tourType" name="tourType">
                                <option value=""></option>
                                <#list tourTypes as type>
                                    <option value="${type}">${type}</option>
                                </#list>
                            </select>
                        </div>
                        <br>
                        <div>
                            <label for="cost" class="uui-big-text">Cost: </label>
                            <input type="number" id="cost" name="cost" placeholder="cost" class="uui-form-element"/>
                        </div>
                        <br>
                        <button onclick="searchTours()" class="uui-button large blue">Search</button>
                    </div>
                    <div id="target" class="hidden">
                        <table class="uui-table dynamic hover records-per-page search showing-pages paging hoverTable"
                               id="tours-table">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Country</th>
                                <th>Date</th>
                                <th>Duration</th>
                                <th>Description</th>
                                <th>Hotel</th>
                                <th>Stars</th>
                                <th>Tour Type</th>
                                <th>Cost</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>
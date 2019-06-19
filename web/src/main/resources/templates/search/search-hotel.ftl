<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.search-hotel"/></title>
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
                        <h1 class="section-title"><@spring.message code="title.search-hotel"/></h1>
                        <div>
                            <label for="name" class="uui-big-text">Hotel name: </label>
                            <input type="text" id="name" name="name" placeholder="name" class="uui-form-element"
                                   pattern=".{3,40}" title="3 to 40 characters"/>
                        </div>
                        <br>
                        <div>
                            <label for="stars" class="uui-big-text">Stars: </label>
                            <input type="number" min="1" max="5" id="stars" name="stars" placeholder="stars"
                                   class="uui-form-element"/>
                        </div>
                        <br>
                        <div>
                            <label for="website" class="uui-big-text">Website: </label>
                            <input type="text" id="website" name="website" placeholder="website"
                                   class="uui-form-element" pattern=".{3,40}" title="3 to 40 characters"/>
                        </div>
                        <br>
                        <button onclick="searchHotels()" class="uui-button large blue">Search</button>
                    </div>
                    <div id="target" class="hidden">
                        <table class="uui-table dynamic hover records-per-page search showing-pages paging hoverTable" id="hotels-table">
                            <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Stars</th>
                                <th>Website</th>
                                <th>Latitude</th>
                                <th>Longitude</th>
                                <th>Features</th>
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
<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.tours"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <script src="/uui/jquery-ui/jquery-dateformat.min.js"></script>
    <script src="/uui/jquery-ui/pagination.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/js/uui-core.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="/uui/css/uui-all.css"/>
    <link rel="stylesheet" href="/uui/fonts/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/uui/js/lib/components/DataTables-1.10.2/css/jquery.dataTables.min.css"/>
    <script src="/uui/js/lib/components/DataTables-1.10.2/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="/uui/css/custom.css"/>
    <script>
        $( document ).ready(function() {
            getPage(1);
        });
        function getPage(page) {
            $.ajax({
                url: 'tours-page/' + page,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    createTourPagination(data);
                }
            });
        }
    </script>
</head>
<body>
<div class="wrapper">
    <#include "../template/header.ftl">
    <div class="uui-main-container">
        <main>
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <h1 class="section-title" style="padding: 10px;"><@spring.message code="title.tours"/></h1>
                    <#if isAdmin>
                        <form action="/import" style="float: left; padding: 5px;">
                            <button type="submit"
                                    class="uui-button blue"><@spring.message code="title.import-tour"/></button>
                        </form>
                        <form action="/create-tour" style="float: left; padding: 5px;">
                            <button type="submit"
                                    class="uui-button blue"><@spring.message code="title.add-tour"/></button>
                        </form>
                    </#if>
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
                                <#list tours as tour>
                                <tr onclick="document.location='/tour/${tour.id?c}'">
                                    <td>${tour.id?c}</td>
                                    <td>${tour.country.name}</a></td>
                                    <td>${tour.date?date?string["dd.MM.yyyy"]}</td>
                                    <td>${tour.duration} days</td>
                                    <td>
                                    <#if tour.description?length gte 30>
                                        ${tour.description?substring(0, 30)}...
                                    <#else>
                                        ${tour.description}
                                    </#if>
                                    </td>
                                    <td><a href="/hotel/${tour.hotel.id?c}">${tour.hotel.name}</a></td>
                                    <td>${tour.hotel.stars}</td>
                                    <td>${tour.tourType}</td>
                                    <td>$${tour.cost}</td>
                                </tr>
                                </#list>
                        </tbody>
                    </table>
                    <br>
                    <div class="text-center" id="pagination"></div>
                </div>
            </div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>

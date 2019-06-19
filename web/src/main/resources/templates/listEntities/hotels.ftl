<#import "/spring.ftl" as spring />
<#include "../template/security.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@spring.message code="title.hotels"/></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script src="/uui/js/lib/jquery-1.12.0.min.js"></script>
    <link rel="stylesheet" href="/uui/bootstrap/css/bootstrap.min.css"/>
    <script src="/uui/bootstrap/js/bootstrap.min.js"></script>
    <script src="/uui/jquery-ui/pagination.js"></script>
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
                url: 'hotels-page/' + page,
                contentType: 'application/json',
                dataType: 'json',
                success: function (data) {
                    createHotelPagination(data);
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
                    <h1 class="section-title" style="padding: 10px;"><@spring.message code="title.hotels"/></h1>
                    <#if isAdmin>
                        <form action="/create-hotel" style="float: left; padding: 5px;">
                            <button type="submit"
                                    class="uui-button blue"><@spring.message code="title.add-hotel"/></button>
                        </form>
                    </#if>
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
                                <#list hotels as hotel>
                                <tr onclick="document.location='/hotel/${hotel.id?c}'">
                                    <td>${hotel.id?c}</td>
                                    <td>${hotel.name}</td>
                                    <td>${hotel.stars?c}</td>
                                    <td>${hotel.website}</td>
                                    <td>${hotel.latitude}</td>
                                    <td>${hotel.longitude}</td>
                                    <td>
                                        <#list hotel.features as feature>
                                                <#if feature?has_next>
                                                    ${feature},
                                                <#else>
                                                    ${feature}
                                                </#if>
                                        </#list>
                                    </td>
                                </tr>
                                </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <br>
            <div class="text-center" id="pagination"></div>
        </main>
    </div>
    <#include "../template/footer.ftl">
</div>
</body>
</html>

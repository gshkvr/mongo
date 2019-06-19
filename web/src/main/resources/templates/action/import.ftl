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
    <link rel="stylesheet" href="/uui/css/lib/components/jasny-bootstrap.min.css"/>
    <script src="/uui/js/lib/components/jasny-bootstrap.min.js"></script>
</head>
<body>
<div class="wrapper">
    <#include "../template/header.ftl">
    <div class="uui-main-container">
        <main class="text-center">
            <div class="uui-login-panel">
                <div class="login-panel-body">
                    <div class="login-panel-section">
                        <h1 class="section-title">Import tours (.csv file)</h1>
                        <form method="POST" action="/import-tour" enctype="multipart/form-data">
                            <div class="uui-file-uploader fileinput fileinput-new" data-provides="fileinput">
                                    <span class="btn btn-file uui-button">
                                        <span class="fileinput-new">
                                            <i class="fa fa-file"></i>Choose...
                                        </span>
                                        <span class="fileinput-exists">
                                            <i class="fa fa-file"></i>Change...
                                        </span>
                                        <input type="file" name="toursFile"/>
                                    </span>
                                <span class="fileinput-filename"></span>
                                <a href="#" class="close fileinput-exists" data-dismiss="fileinput">×</a>
                            </div>
                            <div>
                                <button type="submit" class="uui-button large blue">Import</button>
                            </div>
                        </form>
                        <script>
                            $('.fileinput').fileinput();
                        </script>

                        <#if message?has_content>
                            <h2>${message}</h2>
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

<header>
    <div class="uui-header">
        <nav>
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <a href="/" class="responsive-brand-logo">
                                    <span class="logo">
                                        <img src="/uui/images/ic_logo_UUi.svg" alt=""/>
                                    </span>
                        <span class="title"><@spring.message code="header.name"/></span>
                    </a>
                </div>
            </div>
            <a href="/" class="brand-logo">
                            <span class="logo">
                                <img src="/uui/images/ic_logo_UUi.svg" alt=""/>
                            </span>
                <@spring.message code="header.name"/>
            </a>
            <ul class="uui-header-tools nav navbar-nav">
            <#-- Authentificated -->
                <#if isAuthenticated>
                    <li class="dropdown">
                        <a href="#" class="uui-button dropdown-toggle dark-gray" data-toggle="dropdown">
                                                <@spring.message code="header.search"/>
                            <span class="arrow fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/search-tour"><@spring.message code="title.search-tour"/></a></li>
                            <li><a href="/search-hotel"><@spring.message code="title.search-hotel"/></a></li>
                        </ul>
                    </li>
                </#if>
            <#-- /Authentificated -->
            <#-- Admin -->
                <#if isAdmin>
                    <li class="dropdown">
                        <a href="#" class="uui-button dropdown-toggle dark-gray" data-toggle="dropdown">
                                                <@spring.message code="header.create"/>
                            <span class="arrow fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/create-tour"><@spring.message code="title.add-tour"/></a></li>
                            <li><a href="/create-user"><@spring.message code="title.add-user"/></a></li>
                            <li><a href="/create-country"><@spring.message code="title.add-country"/></a></li>
                            <li><a href="/create-hotel"><@spring.message code="title.add-hotel"/></a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="uui-button dropdown-toggle dark-gray" data-toggle="dropdown">
                            <@spring.message code="header.admin"/>
                            <span class="arrow fa fa-angle-down"></span>
                        </a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="/tours"><@spring.message code="title.tours"/></a></li>
                            <li><a href="/users"><@spring.message code="title.users"/></a></li>
                            <li><a href="/countries"><@spring.message code="title.countries"/></a></li>
                            <li><a href="/hotels"><@spring.message code="title.hotels"/></a></li>
                            <li><a href="/reviews"><@spring.message code="title.reviews"/></a></li>
                        </ul>
                    </li>
                </#if>
            <#-- /Admin -->
            <#-- Member -->
                <li class="dropdown uui-profile-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <div class="profile-photo">
                            <img src="/uui/images/icons/no_photo.png" alt=""/>
                        </div>
                    </a>
                    <div class="dropdown-menu" role="menu">
                        <span class="menu-arrow"></span>
                        <ul class="profile-links">
                            <#if isAuthenticated>
                                <li class="profile"><a href="/profile"><i
                                        class="fa fa-male"></i><@spring.message code="header.profile"/></a></li>
                            </#if>
                            <#if !isAuthenticated>
                                <li class="register"><a href="/register"><i
                                        class="fa fa-registered"></i><@spring.message code="header.register"/></a></li>
                            </#if>
                            <#if isAuthenticated>
                                <li class="logout"><a href="/sign-out"><i
                                        class="fa fa-sign-out"></i><@spring.message code="header.sign-out"/></a></li>
                            </#if>
                            <#if !isAuthenticated>
                                <li class="login"><a href="/sign-in"><i
                                        class="fa fa-sign-in"></i><@spring.message code="header.sign-in"/></a></li>
                            </#if>
                        </ul>
                    </div>
                </li>
            <#-- /Member -->
            <#-- Language -->
                <li class="dropdown">
                    <a href="#" class="uui-button dropdown-toggle dark-gray" data-toggle="dropdown">
                            <@spring.message code="header.locale"/>
                        <span class="arrow fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?locale=en">English</a></li>
                        <li><a href="?locale=ru">Русский</a></li>
                    </ul>
                </li>
            <#-- /Language -->
            </ul>


        </nav>
    </div>
</header>
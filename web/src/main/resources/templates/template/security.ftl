<#assign known = Session.SPRING_SECURITY_CONTEXT??/>
<#if known>
    <#assign
    curUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    username = curUser.getUsername()
    isAdmin = curUser.getAuthorities()?seq_contains('ADMIN')
    isMember = curUser.getAuthorities()?seq_contains('MEMBER')
    isAuthenticated = true />
<#else>
    <#assign
    username = "unknown"
    isAdmin = false
    isMember = false
    isAuthenticated = false/>
</#if>
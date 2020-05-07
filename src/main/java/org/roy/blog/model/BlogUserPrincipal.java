package org.roy.blog.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlogUserPrincipal implements UserDetails {

    private BlogUser blogUser;

    @Autowired
    public BlogUserPrincipal(BlogUser blogUser) {
        this.blogUser = blogUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        // extract list of roles (ROLE_name)
        blogUser.getRoleList().forEach(r -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + r);
            grantedAuthorityList.add(grantedAuthority);
        });

        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return blogUser.getPassword();
    }

    @Override
    public String getUsername() {
        return blogUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return blogUser.isActive();
    }
}

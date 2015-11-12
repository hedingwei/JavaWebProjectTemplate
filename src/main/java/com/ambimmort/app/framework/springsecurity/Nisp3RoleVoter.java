package com.ambimmort.app.framework.springsecurity;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by brett on 2015/8/5.
 */
public class Nisp3RoleVoter extends RoleVoter {


    /**
     * 投票策略: 如果是 ROLE_ADMIN 直接 通过
     * @param authentication
     * @param object
     * @param attributes
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        // Attempt to find a matching granted authority
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if ("ROLE_ADMIN".equals(authority.getAuthority())) { // ROLE_ADMIN 直接 通过
                return ACCESS_GRANTED;
            }
        }


        return super.vote(authentication, object, attributes);
    }
}

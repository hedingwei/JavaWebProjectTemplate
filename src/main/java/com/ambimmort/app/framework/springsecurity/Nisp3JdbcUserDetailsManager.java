package com.ambimmort.app.framework.springsecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.List;

/**
 * Created by brett on 2015/8/5.
 */
public class Nisp3JdbcUserDetailsManager extends JdbcUserDetailsManager {

    /**
     * 添加自定义角色
     * ROLE_USER 为本系统保留角色，管理员使用时应避免使用些名称。
     * 依据现有情况，admin用户不分配任何权限，但实际拥有所有权限。
     * @param username
     * @param authorities
     */
    @Override
    protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities) {


        if ("admin".equals(username)) {
            authorities.clear();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return;
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    }
}

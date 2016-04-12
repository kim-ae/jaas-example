package com.kimae.jaas.principal;

import java.security.Principal;

import com.kimae.jaas.entity.RoleEntity.Role;


public class RolePrincipal implements Principal {
    
    private Role role;
    
    public RolePrincipal(Role role){
        this.role = role;
    }
    
    @Override
    public String getName() {
        return role.toString();
    }

}

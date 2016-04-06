package com.kimae.jaas.model;

import java.util.ArrayList;
import java.util.List;

import com.kimae.jaas.enumerator.Role;

public class User {
    private String login;
    private String password;
    private List<Role> roles;
    
    public User(String login, String password, List<Role> roles){
        this.login = login;
        this.password = password;
        this.roles = new ArrayList<Role>(roles);
    }
    
    
    public String getLogin(){
        return this.login;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public List<Role> getRoles(){
        return new ArrayList<Role>(this.roles);
    }


    public static User getSimpleUser(String login, String password) {
        return new User(login, password, new ArrayList<Role>());
    }
}

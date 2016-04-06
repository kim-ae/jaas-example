package com.kimae.repository;

import java.util.Arrays;

import com.kimae.jaas.enumerator.Role;
import com.kimae.jaas.model.User;
import com.kimae.repository.interfaces.UserRepository;

public class UserDao implements UserRepository {

    @Override
    public User getByLogin(String login) {
        if(login.equals("user")){
            return User.getSimpleUser("user", "pass123");
        }else if(login.equals("admin")){
            return new User("admin", "pass123", Arrays.asList(Role.ADMIN, Role.EXECUTIVE));
        }else{
            return null;
        }
        
    }
}

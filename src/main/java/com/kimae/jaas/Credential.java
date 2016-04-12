package com.kimae.jaas;

import com.kimae.jaas.entity.User;
import com.kimae.repository.interfaces.UserRepository;

public class Credential {
    private UserRepository userRepository;
    
    private String login;
    private String password;
    private User user;
    
    public Credential(String login, String password, UserRepository userRepository){
        this.userRepository = userRepository;
        this.login = login;
        this.password = password;
    }
    
    public boolean isAuthorized(){
        return (getUser() != null) && password.equals(getUser().getPassword());
    }
    
    public User getUser(){
        if(this.user == null){
            this.user = userRepository.getByLogin(login);
        }
        return this.user;
    }
}

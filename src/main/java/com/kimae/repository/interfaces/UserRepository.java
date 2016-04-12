package com.kimae.repository.interfaces;

import javax.ejb.Local;

import com.kimae.jaas.entity.User;

@Local
public interface UserRepository {
    public User getByLogin(String login);
}

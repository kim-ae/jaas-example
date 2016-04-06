package com.kimae.repository.interfaces;

import com.kimae.jaas.model.User;

public interface UserRepository {
    public User getByLogin(String login);
}

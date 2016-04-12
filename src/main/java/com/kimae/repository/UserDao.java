package com.kimae.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kimae.jaas.entity.User;
import com.kimae.repository.interfaces.UserRepository;

@Stateless(name="user-dao")
public class UserDao implements UserRepository {
    
    @PersistenceContext(unitName="Example-PU")
    EntityManager entityManager;

    @Override
    public User getByLogin(String login) {
        return entityManager.createNamedQuery("User.getByLogin", User.class)
                            .setParameter("login", login)
                            .getSingleResult();
    }
}

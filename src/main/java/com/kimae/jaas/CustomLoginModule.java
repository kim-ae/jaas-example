package com.kimae.jaas;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.kimae.jaas.enumerator.Role;
import com.kimae.jaas.model.User;
import com.kimae.jaas.principal.RolePrincipal;
import com.kimae.jaas.principal.UserPrincipal;
import com.kimae.repository.UserDao;


public class CustomLoginModule implements LoginModule{
    
    private CallbackHandler handler;
    private Subject subject;
    private Credential credential;
    
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try{
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            credential = new Credential(name, password, new UserDao());

            if (credential.isAuthorized()) {
                return true;
            }
            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");
        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        User user = credential.getUser();
        subject.getPrincipals().add(new UserPrincipal(user.getLogin()));
        for (Role groupName : user.getRoles()) {
            subject.getPrincipals().add(new RolePrincipal(groupName));
        }
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().clear();
        return true;
    }
}

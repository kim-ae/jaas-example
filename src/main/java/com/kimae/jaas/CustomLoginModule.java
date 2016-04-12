package com.kimae.jaas;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.kimae.helper.CDIHelper;
import com.kimae.jaas.entity.RoleEntity.Role;
import com.kimae.jaas.entity.User;
import com.kimae.jaas.principal.RolePrincipal;
import com.kimae.jaas.principal.UserPrincipal;
import com.kimae.repository.interfaces.UserRepository;


public class CustomLoginModule implements LoginModule{
    
    private CallbackHandler handler;
    private Subject subject;
    private Credential credential;
    @EJB(beanName="user-dao")
    private UserRepository userRepository;
    private static Logger LOG = Logger.getLogger(CustomLoginModule.class.getName());
    
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        this.handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {
        if(userRepository==null){
            try {
                CDIHelper.programmaticInjection(CustomLoginModule.class, this);
            } catch (NamingException e) {
                LOG.severe("it was not possible to manually inject the beans at CustomLoginModule");
            }
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try{
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            credential = new Credential(name, password, userRepository);

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

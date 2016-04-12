package com.kimae.jaas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.kimae.jaas.model.RoleEntity.Role;


@Entity
@Table(name="system_user")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String login;
    @Column
    private String password;
    
    @ManyToMany
    @JoinTable(
            name="user_role_relation",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))
    private List<RoleEntity> roleEntities;
    
    @Transient
    private List<Role> roles;
    
    public User(){}
    
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
        if(this.roles == null){
            this.roles = roleEntities.stream().map(re -> re.getCode()).collect(Collectors.toList());
        }
        return new ArrayList<Role>(this.roles);
    }


    public static User getSimpleUser(String login, String password) {
        return new User(login, password, new ArrayList<Role>());
    }
}

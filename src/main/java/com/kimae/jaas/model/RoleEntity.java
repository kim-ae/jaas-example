package com.kimae.jaas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RoleEntity {
    @Id
    @Column
    private int id;
    @Column
    @Enumerated(EnumType.STRING)
    private Role code;
    
    @ManyToMany(mappedBy="roleEntities")
    private List<User> users;
    
    public RoleEntity(){}
    
    public Role getCode(){
        return this.code;
    }
    static public enum Role {
        ADMIN, EXECUTIVE
    }

}

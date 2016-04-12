package com.kimae.jaas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="system_role")
public class RoleEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

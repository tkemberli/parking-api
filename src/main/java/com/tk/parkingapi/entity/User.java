package com.tk.parkingapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * A User entity to be queried from the database
 * This entity is then used in the {@link SecurityUser} class in order to grant Spring Security privileges
 */

@Entity
@Table(name = "user")
@NoArgsConstructor
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    private String authority;

    @Column(name = "enabled")
    private Boolean isEnabled;
}

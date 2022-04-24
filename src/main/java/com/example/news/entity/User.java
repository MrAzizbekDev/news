package com.example.news.entity;

import com.example.news.entity.enums.PermissionEnum;
import com.example.news.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Role role;

    public User(String fullName, String username, String password, Role role,boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled=enabled;
    }

    private boolean enabled=true;
    private boolean AccountNonExpired=true;
    private boolean AccountNonLocked=true;
    private boolean CredentialsNonExpired=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        var lavozimlarList = role.getLavozimlarList();
        Set<GrantedAuthority> list = new HashSet<>();
        for (PermissionEnum permissionEnum : lavozimlarList) {
            list.add(new SimpleGrantedAuthority(permissionEnum.name()));
        }
        return list;
    }


}

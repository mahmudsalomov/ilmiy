package com.ilmiy.model.user;

import com.ilmiy.model.FileStorage;
import com.ilmiy.model.template.AbsEntityUUID;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AbsEntityUUID implements UserDetails {

    @NotNull
    @Column(unique = true)
    private String username;


    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(columnDefinition = "text")
    private String fullName;

    @Column(columnDefinition = "text")
    private String bio;

    @Column(columnDefinition = "text")
    private String address;

    private String phone;

    @OneToOne
    private FileStorage image;

    private boolean active=true;


    @Column(columnDefinition = "text")
    private String socialNetworks;

    @ManyToMany
    private Set<Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}

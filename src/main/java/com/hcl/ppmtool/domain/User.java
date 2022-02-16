package com.hcl.ppmtool.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Email(message = "Username needs to be an email")
    @NotBlank(message = "username is required")
    @Column(unique = true)
    @Getter @Setter private String username;

    @NotBlank(message = "Please enter your full name")
    @Getter @Setter private String fullName;

    @NotBlank(message = "Password field is required")
    @Getter @Setter private String password;

    @Transient
    @Getter @Setter private String confirmPassword;
    @Getter @Setter private Date create_At;
    @Getter @Setter private Date update_At;

    //OneToMany with Project
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }

    /*
    UserDetails interface methods
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}

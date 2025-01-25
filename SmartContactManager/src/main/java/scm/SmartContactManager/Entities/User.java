package scm.SmartContactManager.Entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "user")
@Table(name = "users")
public class User implements UserDetails {




    public String getname() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Id
    private String userid;
//@Column(unique = true,nullable = false)
//    private String username;
//    @Column(unique = true,nullable = false)
//
//    private String email;
//    private String password;
//    private String about;
//    private String profile;
//    private String phone;
//
//    //=======
//
//
//    private boolean enabled = false;
//
//
//    private boolean emailverified;
//
//
//    private boolean phoneverified;



    @Column(name = "username", nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String about;
    private String profile;
    private String phone;

    private boolean enabled = false;
    private boolean emailverified = false;
    private boolean phoneverified = false;
@Enumerated(value = EnumType.STRING)
    private Provider provider = Provider.SELF;
    private String providerId;

    public void setUsername(String username) {
        this.username=username;
    }

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRolelist() {
        return rolelist;
    }

    public void setRolelist(List<String> rolelist) {
        this.rolelist = rolelist;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> rolelist=new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> roles=rolelist.stream().map(role->new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEmailverified() {
        return emailverified;
    }

    public void setEmailverified(boolean emailverified) {
        this.emailverified = emailverified;
    }

    public boolean isPhoneverified() {
        return phoneverified;
    }

    public void setPhoneverified(boolean phoneverified) {
        this.phoneverified = phoneverified;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(unique = true)
    private String token;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();


}

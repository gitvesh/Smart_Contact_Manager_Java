package scm.SmartContactManager.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String phonenumber;
    private String address;
    private String picture;
    private String discription;
    private boolean favourite;


    private String weblink;
    private String LinkedinLink;

    public String getCloudinaryImagePublicId() {
        return CloudinaryImagePublicId;
    }

    public void setCloudinaryImagePublicId(String cloudinaryImagePublicId) {
        CloudinaryImagePublicId = cloudinaryImagePublicId;
    }

    private String CloudinaryImagePublicId;
    @ManyToOne
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getLinkedinLink() {
        return LinkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        LinkedinLink = linkedinLink;
    }

    public User getU() {
        return user;
    }

    public void setU(User u) {
        this.user = u;
    }

    public List<Links> getLink() {
        return link;
    }

    public void setLink(List<Links> link) {
        this.link = link;
    }

    @OneToMany(mappedBy = "contacts",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Links> link=new ArrayList<>();
}

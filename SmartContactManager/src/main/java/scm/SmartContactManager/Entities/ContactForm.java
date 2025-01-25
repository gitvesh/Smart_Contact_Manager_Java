package scm.SmartContactManager.Entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public class ContactForm {

    private String cid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public boolean isFav() {
        return isFav;
    }

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email Address")
    private String email;
    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phone;
    private String Address;
    private String Description;
    private boolean isFav;
    private String weblink;
    private String LinkedinLink;

    private MultipartFile profile;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean getisFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
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

    public MultipartFile getProfile() {
        return profile;
    }


    public void setProfile(MultipartFile profile) {
        this.profile = profile;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private String picture;


}


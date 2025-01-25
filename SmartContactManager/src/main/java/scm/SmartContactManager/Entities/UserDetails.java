package scm.SmartContactManager.Entities;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;




public class UserDetails {

@NotBlank(message = "UserName is Required")
@Size( min = 3,message = "Minimum 3 Character is Required")
    private String name;


@Email(message = "Email is Invalid")
    private String email;



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

    public String getPassword() {
        return password;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    @NotBlank(message = "Password is Required")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters")
    private String password;
    @NotBlank(message = "About is Required")
    private String about;

    @Size(max = 12,min =8,message = "Invalid Phone Number")
    private String phone_number;
}

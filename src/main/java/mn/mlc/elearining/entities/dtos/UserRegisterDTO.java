package mn.mlc.elearining.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import mn.mlc.elearining.validation.passwordMatch.PasswordMatch;
import mn.mlc.elearining.validation.uniqueEmail.ValidateUniqueEmail;
import mn.mlc.elearining.validation.uniqueUsername.ValidateUniqueUsername;

@PasswordMatch(password = "password",confirmPassword = "confirmPassword")
public class UserRegisterDTO {
    @NotNull
    @Size(min = 2,message = "Username length at least 2 characters!")
    @ValidateUniqueUsername
    private String username;
    @NotNull
    @Size(min = 2, message = "Password length is at least 2 characters!")
    private String password;
    @NotNull
    @Email
    @ValidateUniqueEmail
    private String email;
    @NotNull
    private String confirmPassword;
    @Positive(message = "must be positive number")
    private int age;
    private String fullName;

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserRegisterDTO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegisterDTO setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
}

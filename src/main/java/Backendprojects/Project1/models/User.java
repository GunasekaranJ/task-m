package Backendprojects.Project1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usertable")
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    // No-args constructor
    public User() {}

    // All-args constructor
    public User(Long user_id, String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    // Builder pattern manually
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long user_id;
        private String email;
        private String password;

        public UserBuilder user_id(Long user_id) {
            this.user_id = user_id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(user_id, email, password);
        }
    }
}

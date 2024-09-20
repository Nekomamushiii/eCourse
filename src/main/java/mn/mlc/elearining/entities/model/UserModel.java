package mn.mlc.elearining.entities.model;

import mn.mlc.elearining.entities.Role;

import java.util.List;

public class UserModel {
    private Long id;
    private String username;
    private List<Role> roles;

    public UserModel(Long id, String username, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public UserModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserModel setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }
}

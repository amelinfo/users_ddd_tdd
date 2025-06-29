package com.ameltaleb.users.domain.model;

public class User {
    
    private Long id;
    private String name;
    private String email;
    private boolean active;

    
    public User() {
    }

    public User(Long id, String name, String email, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active= isActive;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
    return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", active=" + active + "]";
    }
    
    
}

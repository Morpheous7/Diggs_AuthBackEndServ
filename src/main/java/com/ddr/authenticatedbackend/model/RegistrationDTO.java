package com.ddr.authenticatedbackend.model;


public class RegistrationDTO {
    private String username;
     private String password;
    private String authority;
     public RegistrationDTO(){
         super();
     }
    public RegistrationDTO(String username, String password) {
         super();
        this.username = username;
        this.password = password;
        this.authority = null;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationDTO info{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                '}';
    }
}

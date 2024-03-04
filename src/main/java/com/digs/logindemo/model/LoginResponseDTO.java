package com.digs.logindemo.model;


import java.util.Objects;


public class LoginResponseDTO {
    private SecurityUser user;
    private String jwt;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(SecurityUser user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public LoginResponseDTO(SecurityUser user) {
        this.user = user;
        this.jwt = null;
    }

    public User getUser() {
        return this.user.user;
    }

    public SecurityUser getSecUser() {
        return this.user;
    }
    public void setUser(SecurityUser user) {
        this.user = user;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginResponseDTO that)) return false;
        return getUser().equals(that.getUser()) && getJwt().equals(that.getJwt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getJwt());
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "user=" + this.user +
                ", jwt='" + this.jwt + '\'' +
                '}';
    }
}

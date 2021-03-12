package com.dev.lembrete.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.dom4j.tree.AbstractEntity;

import javax.validation.constraints.NotEmpty;

public class AccountCredentials extends AbstractEntity
{
    @NotEmpty
    private String username;
    @NotEmpty
    @JsonIgnore
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

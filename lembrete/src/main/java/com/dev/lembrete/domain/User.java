package com.dev.lembrete.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "user_password")
    private String userPassword;

    @NotNull
    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "enable")
    private boolean enable;

    @NotNull
    @Column(name = "user_cpf")
    private String userCpf;


    //Ignorar variavel users em roles
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"users"})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
/*
    @OneToMany
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "lembrete_id"))
    private List<Lembrete> lembretes;*/

    @Nullable
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_carrinho")
    private CarrinhoCliente carrinhoCliente;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User(){}

    public User(@NotEmpty String userName, @NotEmpty String userPassword, @NotEmpty String userEmail, boolean enable)
    {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.enable = enable;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //Ignorar get no Json
    @JsonIgnore
    public String getUserPassword() {
        return userPassword;
    }

    //Permitir o metodo set no jSON
    @JsonProperty
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public CarrinhoCliente getCarrinhoCliente() {
        return carrinhoCliente;
    }

    public void setCarrinhoCliente(CarrinhoCliente carrinhoCliente) {
        this.carrinhoCliente = carrinhoCliente;
    }
}

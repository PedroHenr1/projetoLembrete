package com.dev.lembrete.service;

import com.dev.lembrete.domain.CarrinhoCliente;
import com.dev.lembrete.domain.Role;
import com.dev.lembrete.domain.User;
import com.dev.lembrete.repository.RoleRepository;
import com.dev.lembrete.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ItemCarrinhoService itemCarrinhoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*                                      Auth system                                         */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try
        {
            User user = userRepository.findUserByUserName(username);
            if(user == null) return null;
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), getAuthories(user));
        }
        catch(Exception ex)
        {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }
    }

    private Set<GrantedAuthority> getAuthories(User user)
    {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: user.getRoles())
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getRoleName());
            authorities.add(grantedAuthority);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+authorities.toString());
        return authorities;
    }

    /*                 ^^^^                     Auth system                ^^^^                     */

    public User saveUser(User user)
    {
        Role roleAdmin = roleRepository.findByRoleName("ADMIN");
        List<Role> listRole = new ArrayList<>();
        CarrinhoCliente carrinhoCliente = new CarrinhoCliente(null, 1.0);
        user.setCarrinhoCliente(carrinhoCliente);
        listRole.add(roleAdmin);
        user.setRoles(listRole);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
        //userRepository.saveUserRole(user.getUserId(), roleAdmin.getRoleId());
        return user;
    }

    public User updateUser(User user)
    {
        if(user != null) return userRepository.save(user);
        throw new RuntimeException("Não foi possivel encontrar o usuario.");
    }

    public void deleteUserById(Long id)
    {
        userRepository.deleteById(id);
        throw new RuntimeException("Não foi possivel encontrar o usuario.");
    }

    public List<User> listUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        if(id != null) return userRepository.findById(id).get();
        throw new RuntimeException("Não foi possivel encontrar o usuario.");
    }

    public User getUserByName(String nome)
    {
        return userRepository.findUserByUserName(nome);
    }


   /* public Item addItemCarrinho(String token, Long carrinhoId)
    {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByUserName(TokenAuthenticationService.getTokenUsername(token)));
        if(user.isPresent())
        {

        }
    }*/
}

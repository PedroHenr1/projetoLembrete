package com.dev.lembrete.service;

import com.dev.lembrete.domain.CarrinhoCliente;
import com.dev.lembrete.domain.Item;
import com.dev.lembrete.domain.ItemCarrinho;
import com.dev.lembrete.domain.User;
import com.dev.lembrete.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ItemCarrinhoService
{

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    private UserService userService;

    public ItemCarrinho addItemCarrinho(ItemCarrinho item, String token)
    {
        String userName = TokenAuthenticationService.getTokenUsername(token);
        User user = userService.getUserByName(userName);
        item.setCarrinhoCliente(user.getCarrinhoCliente());
        System.out.println(""+item.getItemCarrinho()+" "+item.getCarrinhoCliente());
        return itemCarrinhoRepository.save(item);
    }
}

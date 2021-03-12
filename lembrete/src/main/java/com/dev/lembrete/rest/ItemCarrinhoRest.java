package com.dev.lembrete.rest;

import com.dev.lembrete.domain.Item;
import com.dev.lembrete.domain.ItemCarrinho;
import com.dev.lembrete.repository.ItemCarrinhoRepository;
import com.dev.lembrete.service.ItemCarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrinho")
public class ItemCarrinhoRest {

    @Autowired
    private ItemCarrinhoService itemCarrinhoService;

    @RequestMapping("/save")
    public ResponseEntity<ItemCarrinho> saveItemCarrinho(@RequestBody ItemCarrinho item, @RequestHeader("Authorization") String token)
    {
        return ResponseEntity.ok(itemCarrinhoService.addItemCarrinho(item, token));
    }
}

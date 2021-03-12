package com.dev.lembrete.service;

import com.dev.lembrete.domain.Item;
import com.dev.lembrete.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService
{
    @Autowired
    private ItemRepository itemRepository;

    //ADMIN / FUNCIONARIO
    public Item saveItem(Item item)
    {
        return itemRepository.save(item);
    }

    //ADMIN / FUNCIONARIO
    public Item updateItem(Item item)
    {
        Optional<Item> itemExist = itemRepository.findById(item.getItemId());
        if(itemExist.isPresent())
            return itemRepository.save(item);
        return null;
    }

    //ADMIN / FUNCIONARIO
    public boolean deleteItembyId(Long itemId)
    {
        Optional<Item> itemExist = itemRepository.findById(itemId);
        if(itemExist.isPresent()) {
            itemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    //ALL
    public List<Item> listItem()
    {
        return itemRepository.findAll();
    }

    //ADMIN
    public Item getItemById(Long itemId)
    {
        Optional<Item> itemExist = itemRepository.findById(itemId);
        if(itemExist.isPresent())
            return itemExist.get();
        return null;
    }
}

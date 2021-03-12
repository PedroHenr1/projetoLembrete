package com.dev.lembrete.rest;

import com.dev.lembrete.domain.Item;
import com.dev.lembrete.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemRest
{
    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item)
    {
        return ResponseEntity.ok(itemService.saveItem(item));
    }

    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item)
    {
        return ResponseEntity.ok(itemService.updateItem(item));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteItem(Long itemId)
    {
        if(itemService.deleteItembyId(itemId))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> listItem()
    {
        return ResponseEntity.ok(itemService.listItem());
    }

    @GetMapping("/get")
    public ResponseEntity<Item> getItemById(Long itemId)
    {
        Item item = itemService.getItemById(itemId);
        if(item == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(item);
    }
}

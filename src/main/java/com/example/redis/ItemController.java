package com.example.redis;

import com.example.redis.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> readAll() {
        return itemService.readAll();
    }

    @GetMapping("{id}")
    public ItemDto readOne(
            @PathVariable("id")
            Long id
    ) {
        return itemService.readOne(id);
    }

    @PostMapping
    public ItemDto create(
            @RequestBody
            ItemDto itemDto
    ){
        return itemService.create(itemDto);
    }
}

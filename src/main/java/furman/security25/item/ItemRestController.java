package furman.security25.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@author   User
@project   security25
@class  ItemRestController
@version  1.0.0
@since 18.11.2025 - 06.17
*/
@RestController
@RequestMapping("/api/v1/items")
@AllArgsConstructor

public class ItemRestController {
    private final ItemService service;

    @GetMapping
    public List<Item> getItems() {
        return service.getAll();
    }@GetMapping("/{id}")
    public Item getOneItem(@PathVariable String id) {
        return service.getById(id);
    }@DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }
    @PostMapping
    public Item saveItem(@RequestBody Item item) {
        return service.create(item);
    }
    @PutMapping
    public Item update(@RequestBody Item item) {
        return service.update(item);
    }
}

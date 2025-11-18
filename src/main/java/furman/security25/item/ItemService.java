package furman.security25.item;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
@author   User
@project   security25
@class  ItemService
@version  1.0.0
@since 18.11.2025 - 06.15
*/
@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository Repository;

    private List<Item> items;

    @PostConstruct
    void init() {
        items.add(new Item("1","name1","description"));
        items.add(new Item("2","name2","description2"));
        items.add(new Item("3","name3","description3"));
        Repository.saveAll(items);
    }

    public List<Item> getAll() {
        return Repository.findAll();
    }

    public Item getById(String id) {
        return Repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        Repository.deleteById(id);
    }

    public Item create(Item item) {
        return Repository.save(item);
    }

    public Item update(Item item) {
        return Repository.save(item);
    }
}

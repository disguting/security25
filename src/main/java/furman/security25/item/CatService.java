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
public class CatService {
    private final CatRepository Repository;

    private List<Cat> cats;

    @PostConstruct
    void init() {
        cats.add(new Cat("1","Cat1","black"));
        cats.add(new Cat("2","Cat2","ginger"));
        cats.add(new Cat("3","Cat3","white"));
        Repository.saveAll(cats);
    }

    public List<Cat> getAll() {
        return Repository.findAll();
    }

    public Cat getById(String id) {
        return Repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        Repository.deleteById(id);
    }

    public Cat create(Cat cat) {
        return Repository.save(cat);
    }

    public Cat update(Cat cat) {
        return Repository.save(cat);
    }
}

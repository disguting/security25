package furman.security25.item;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
/*
@author   User
@project   security25
@class  AdminCatRestController
@version  1.0.0
@since 19.11.2025 - 22.11
*/

@RestController
@RequestMapping("/api/v1/admin/cats")
@AllArgsConstructor
public class AdminCatRestController {
    private final CatService service;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Cat createCat(@RequestBody Cat cat) {
        return service.create(cat);
    }

    @PutMapping
    public Cat update(@RequestBody Cat cat) {
        return service.update(cat);
    }

    @GetMapping("admin")
    public String HelloAdmin(){
        return "Hello Admin!";
    }
}
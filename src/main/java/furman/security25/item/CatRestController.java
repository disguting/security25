package furman.security25.item;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/v1/cats")
@AllArgsConstructor


public class CatRestController {
    private final CatService service;

    @GetMapping
    public List<Cat> getItems() {
        return service.getAll();
    }@GetMapping("/{id}")
    public Cat getOneItem(@PathVariable String id) {
        return service.getById(id);
    }@DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }
    @PostMapping
    public Cat saveItem(@RequestBody Cat cat) {
        return service.create(cat);
    }
    @PutMapping
    public Cat update(@RequestBody Cat cat) {
        return service.update(cat);
    }

    @GetMapping("/hello/user")
    @PreAuthorize("hasAnyRole('USER', 'SUPERADMIN')")
    public String helloUser() {
        return "Hello User!";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPERADMIN')")
    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @GetMapping("hello/stranger")
    public String helloStranger() {
        return "Hello Stranger!";
    }


}

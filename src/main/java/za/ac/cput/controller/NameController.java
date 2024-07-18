package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Name;
import za.ac.cput.service.NameService;

import java.util.List;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    private NameService nameService;

    @PostMapping("/save")
    public Name save(@RequestBody Name name) {
        return nameService.create(name);
    }

    @GetMapping("/read/{firstName}")
    public Name read(@PathVariable String firstName) {
        return nameService.read(firstName);
    }

    @PutMapping("/update")
    public Name update(@RequestBody Name name) {
        return nameService.update(name);
    }

    @DeleteMapping("/delete/{firstName}")
    public void delete(@PathVariable String firstName) {
        nameService.delete(firstName);
    }

    @GetMapping("/getAll")
    public List<Name> getAll() {
        return nameService.getAll();
    }
}

package Controller;

import Service.Facade;
import Entities.Type;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/{type}")
public class TypeController {

    private final Facade agroFacade;

    public TypeController(Facade agroFacade) {
        this.agroFacade = agroFacade;
    }

    @PostMapping("/transfer")
    public String transfer(@PathVariable("type") String type,
                           @RequestParam("fromId") int fromId,
                           @RequestParam("toId") int toId,
                           @RequestParam("amount") int amount) {
        agroFacade.transfer(type, fromId, toId, amount);
        return "Переведення успішно виконано!";
    }

    @GetMapping
    public List<? extends Type> getAll(@PathVariable("type") String type) {
        return agroFacade.getAll(type);
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable("type") String type, @PathVariable("id") int id) {
        return agroFacade.getById(type, id);
    }

    @PostMapping
    public String add(@PathVariable("type") String type, @RequestBody Map<String, Object> data) {
        agroFacade.save(type, data, false);
        return "Об'єкт типу " + type + " успішно додано!";
    }

    @PutMapping
    public String update(@PathVariable("type") String type, @RequestBody Map<String, Object> data) {
        agroFacade.save(type, data, true);
        return "Об'єкт типу " + type + " оновлено!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("type") String type, @PathVariable("id") int id) {
        agroFacade.delete(type, id);
        return "Об'єкт видалено успішно!";
    }


    @GetMapping("/{id}/profit")
    public Map<String, Object> getProfit(@PathVariable("type") String type,
                                         @PathVariable("id") int id,
                                         @RequestParam("days") int days) {
        return agroFacade.calculateProfit(type, id, days);
    }
}
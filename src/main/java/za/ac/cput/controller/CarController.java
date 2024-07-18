package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Car;
import za.ac.cput.service.CarService;
import java.util.List;
@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;
    @PostMapping("/create")
    public Car create(@RequestBody Car car){
        return carService.create(car);
    }
    @GetMapping("/read/{carID}")
    public Car read(@PathVariable Long carID){
        return carService.read(carID);
    }
    @PutMapping("/update")
    public Car update(@RequestBody Car car){
        return carService.update(car);
    }
    @DeleteMapping("/delete/{carID}")
    public void delete (@PathVariable Long carID){
        carService.delete(carID);
    }
   @GetMapping("/getAll")
    public List<Car> getAll(){
        return carService.getAll();
    }
}

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
    @PostMapping("/save")
    public Car save(@RequestBody Car car){
        return carService.create(car);
    }
    @GetMapping("/read/{carId}")
    public Car read(@PathVariable String carId){
        return carService.read(carId);
    }
    @PutMapping("/update")
    public Car update(@RequestBody Car car){
        return carService.update(car);
    }
    @DeleteMapping("/delete/{carId}")
    public void delete (@PathVariable String carId){
        carService.delete(carId);
    }
    @GetMapping("/getAllCars")
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }
}

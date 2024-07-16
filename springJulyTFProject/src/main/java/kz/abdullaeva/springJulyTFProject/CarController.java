package kz.abdullaeva.springJulyTFProject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/car")
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "home";
    }

    @GetMapping(value = "/addCarPage")
    public String openAddCar(Model model) {
        return "addCar";
    }

    @PostMapping(value = "/addCar")
    public String addCar(@RequestParam("model") String model,
                         @RequestParam("year") int year,
                         @RequestParam("price") int price) {
        Car car = new Car();
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);

        carRepository.save(car);
        return "redirect:/car/home";
    }

}

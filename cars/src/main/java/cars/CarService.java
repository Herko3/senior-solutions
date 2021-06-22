package cars;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars  = List.of(
            new Car("Opel","Astra",15,CarCondition.NORMAL,null),
            new Car("Peugeot","407",10,CarCondition.BAD,null),
            new Car("Tesla","Model 3",5,CarCondition.PERFECT,null),
            new Car("Tesla","Model 5",5,CarCondition.PERFECT,null),
            new Car("Ford","Mustang GT",2,CarCondition.PERFECT,null)
            );

    public List<Car> getCars() {
        return cars;
    }

    public List<String> getBrands(){
        return cars.stream()
                .map(Car::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }
}

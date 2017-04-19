package Spring;

import org.springframework.stereotype.Component;

/**
 * Created by tianf on 2016/11/2.
 */

@Component
public class Car {

    private String brand;
    private int maxSpeed;

    public Car(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [brand=" + brand + ", maxSpeed=" + maxSpeed + "]";
    }
}

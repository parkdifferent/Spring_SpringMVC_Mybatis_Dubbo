package Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tianf on 2016/11/2.
 */

@Component
public class Person {

    private String name;
    private int age;
    @Autowired
    private Car car;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
    }


}



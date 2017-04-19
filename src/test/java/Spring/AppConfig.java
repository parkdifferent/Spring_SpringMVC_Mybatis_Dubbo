package Spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tianf on 2016/11/2.
 */

@Configuration
public class AppConfig {

    @Bean
    public Car car() {
        return new Car("Benz", 320);
    }

    @Bean
    public Person person() {
        return new Person("骆昊", 34);
    }
}

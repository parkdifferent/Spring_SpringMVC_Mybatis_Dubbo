package Spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by tianf on 2016/11/2.
 */
public class Test {
    public static void main(String[] args) {
        // TWR (Java 7+)

            ConfigurableApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
            Person person = factory.getBean(Person.class);
            System.out.println(person);

    }

}

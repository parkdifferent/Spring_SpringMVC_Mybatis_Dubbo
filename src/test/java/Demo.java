import com.szkingdom.ssm.domain.Person;
import com.szkingdom.ssm.repo.PersonRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Created by phoenix on 2017/4/1.
 */
public class Demo {

    public static void main(String[] args) {
        ConfigurableApplicationContext ct = new ClassPathXmlApplicationContext(
                "application.xml");
        PersonRepository repository = ct.getBean(PersonRepository.class);

        for(int i = 0 ; i < 8; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setAge(20+ i );
            person.setName("phoenix" + i);
            repository.save(person);
        }


        ct.close();
    }

}

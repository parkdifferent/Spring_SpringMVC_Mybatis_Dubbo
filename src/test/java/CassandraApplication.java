import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.szkingdom.ssm.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

import java.util.UUID;

/**
 * Created by phoenix on 2017/3/31.
 */
public class CassandraApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApplication.class);

    protected static Person newPerson(String name, int age) {
        return newPerson(UUID.randomUUID().toString(), name, age);
    }

    protected static Person newPerson(String id, String name, int age) {
        return new Person(id, name, age);
    }

    public static void main(String[] args) {

        Cluster cluster = Cluster.builder().addContactPoints("localhost").build();
        Session session = cluster.connect("persondb");

        CassandraOperations template = new CassandraTemplate(session);

        Person jonDoe = template.insert(newPerson("phoenix", 24));

        /*Select selectStatement = QueryBuilder.select().from("person");
        selectStatement.where(QueryBuilder.eq("id", jonDoe.getId()));

        LOGGER.info(template.queryForObject(selectStatement, Person.class).getId());*/

        template.truncate("person");
        session.close();
        cluster.close();
    }
}

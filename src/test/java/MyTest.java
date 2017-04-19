
import com.szkingdom.ssm.dao.UserMapper;
import com.szkingdom.ssm.domain.*;
import com.szkingdom.ssm.entity.*;
import com.szkingdom.ssm.entity.Item;
import com.szkingdom.ssm.repo.PersonRepository;
import com.szkingdom.ssm.repository.OrderRepository;
import com.szkingdom.ssm.service.IRecordService;
import com.szkingdom.ssm.service.IUserService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by tianf on 2016/10/29.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:application.xml"})
public class MyTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static final String BASE_URL = "https://www.zhihu.com/";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;


    @Autowired
    private JavaMailSenderImpl sender;

    @Autowired
    private IRecordService recordService;

    @Autowired
    MongoOperations mongo;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CassandraOperations cassandraOperations;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    HttpSolrServer httpSolrServer;

    @Test
    public void testSolrj() throws SolrServerException {

        SolrQuery query = new SolrQuery();
        String keyWord = "HTC";
        query.setQuery("title:"+ keyWord);

        QueryResponse response = httpSolrServer.query(query);
      /*  SolrDocumentList results = response.getResults();
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }*/
        List<com.szkingdom.ssm.domain.Item> items = response.getBeans(com.szkingdom.ssm.domain.Item.class);
        for (int i = 0;i <items.size(); i++) {
            System.out.println(items.get(i));
        }


    }




    @Test
    public void testCassandra() {
        //cassandraOperations.insert(new Person(UUID.randomUUID().toString(),"phoenix",24));
        /*for(int i = 0 ; i < 8; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setAge(20+ i );
            person.setName("test" + i);
            cassandraOperations.insert(person);
        }*/

        /*select all*/
        String cqlAll = "select * from person";
        List<Person> results = cassandraOperations.select(cqlAll, Person.class);
        for (Person p : results) {
            LOG.info(String.format("Found People with Name [%s] for id [%s]", p.getName(), p.getId()));
        }

        /*select one*/
        /*String cqlOne = "select * from person where id = '9324c5f6-7f86-4bfb-9cca-54a6b9e5031e'";
        Person p = cassandraOperations.selectOne(cqlOne, Person.class);
        LOG.info(String.format("Found Person with Name [%s] for id [%s]", p.getName(), p.getId()));
*/


        /*Update update = QueryBuilder.update("person");
        update.setConsistencyLevel(ConsistencyLevel.ONE);
        update.where(QueryBuilder.eq("id", "22a1a02d-83eb-481b-8a8a-537dbffa25fb"));
        update.with(QueryBuilder.set("age", 26));

        cassandraOperations.execute(update);*/


        /*Delete delete = QueryBuilder.delete().from("person");
        delete.where(QueryBuilder.eq("id", "22a1a02d-83eb-481b-8a8a-537dbffa25fb"));

        cassandraOperations.execute(delete);*/
    }

    @Test
    public void testCassandraRepository() {
    /*    for(int i = 0 ; i < 8; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().toString());
            person.setAge(20+ i );
            person.setName("test" + i);
            personRepository.save(person);
        }*/

            List<Person> p = (List<Person>) personRepository.findAll();


    }




    @Test
    public void testMongo() {
        for(int j = 0; j < 5 ;j++) {
            Order order = new Order();
            order.setCustomer("phoenix"+j);
            order.setType("WEB");

            Set<Item> items = new LinkedHashSet<>();
            for(int i = 0;i< 5; i++) {
                Item item = new Item();
                item.setProduct("iphone" + i);
                item.setQuantity(Integer.valueOf(i+1));
                item.setPrice(Double.valueOf(5000 * (i+1)));
                items.add(item);

                order.setItems(items);
            }

            mongo.save(order,"order");
        }


       /* Order order = new Order();
        order.setCustomer("phoenix");
        order.setType("WEB");

        Set<Item> items = new LinkedHashSet<>();
        for(int i = 0;i< 5; i++) {
            Item item = new Item();
            item.setProduct("iphone" + i+1);
            item.setQuantity(Integer.valueOf(i+1));
            item.setPrice(Double.valueOf(5000 * (i+1)));
            items.add(item);

            order.setItems(items);
        }



        mongo.save(order,"order");*/
    }

    @Test
    public void testMongo1() {
        long orderCount = mongo.getCollection("order").count();
    }

    @Test
    public void testFind() {
        List<Order> orders = mongo.find(Query.query(Criteria.where("customer").is("phoenix4").and("type").is("WEB")),Order.class);
        if( orders != null && orders.size() > 0) {
            Order order = orders.get(0);
            mongo.remove(order);
        }
    }

    @Test
    public void testRepository() {
        List<Order> orders = orderRepository.findAll();
        if(orders != null && orders.size() > 0) {
            for(int i = 0;i < orders.size();i++) {
                System.out.println(orders.get(i) + "" + orders.get(i).getItems().toString());

            }
        }
    }

    @Test
    public void testRepository1() {
        Order order = new Order();
        order.setCustomer("test");
        order.setType("NET");

        Set<Item> items = new LinkedHashSet<>();
        for(int i = 0;i< 5; i++) {
            Item item = new Item();
            item.setProduct("apple" + (i+1));
            item.setQuantity(Integer.valueOf(i+1));
            item.setPrice(Double.valueOf(5000 * (i+1)));
            items.add(item);
            order.setItems(items);
        }
        orderRepository.save(order);
    }

    @Test
    public void testBy() {
        //List<Order> orders = orderRepository.findByCustomer("test");
        //List<Order> orders = orderRepository.findByCustomerLike("phoenix");
        //List<Order> orders = orderRepository.findByCustomerAndType("phoenix1","WEB");
        //List<Order> orders = orderRepository.findByCustomerLikeAndType("phoenix","WEB");
        List<Order> orders = orderRepository.findUserOrder("test","WEB");
        if(orders != null && orders.size() > 0) {
            for(int j = 0; j< orders.size(); j++) {
                System.out.println(orders.get(j).toString());
                System.out.println(orders.get(j).getItems().toString());
            }

        }
    }


















    @Test
    public void testEmail() {
        // 构建简单邮件对象，见名知意
        SimpleMailMessage message = new SimpleMailMessage();
// 设定邮件参数
        message.setFrom(sender.getUsername());
        message.setTo("parkdifferent1@gmail.com");
        message.setSubject("Hello world");
        message.setText("Hello world via spring mail sender");
        sender.send(message);

    }


    @Test
    public void test() {
        User user =new User();
        user.setUserName("hello");
        user.setUserPassword("135");
        user.setUserEmail("qwer@163.com");
        userMapper.insertSelective(user);
    }



    @Test
    public void test2() {
        User user =new User();
        user.setUserName("world");
        user.setUserPassword("789");
        user.setUserEmail("asdf@163.com");
        userService.insertSelective(user);
    }

    @Test
    public void test3() {
        User user = userService.selectByPrimaryKey(1);
        LOG.info(user.toString());
    }

    @Test
    public void test4() {
        List<User> userList= userService.selectUserList();
        for(User user : userList) {
            LOG.info(user.toString());
        }

        List<User> userList1= userService.selectUserList();
        for(User user : userList1) {
            LOG.info(user.toString());
        }

        List<User> userList2= userService.selectUserList();
        for(User user : userList2) {
            LOG.info(user.toString());
        }
    }

    @Test
    public void test5() {
        User user = new User();
        user.setUserId(6);
        user.setUserName("clearLove");
        user.setUserEmail("Loveyou@163.com");
        userService.updateByPrimaryKeySelective(user);
    }

    @Test
    public void test6() {
        userService.deleteByPrimaryKey(5);
    }


    @Test
    public void testEhcache() {

        /*使用缓存*/
        /**
         * Initializing EhCache CacheManager
         * Closing org.springframework.context.support.GenericApplicationContext@5c909414: startup date [Sun Oct 30 14:04:15 CST 2016]; root of context hierarchy
         * Shutting down EhCache CacheManager
         *
         */

        LOG.debug("Result : {}",  userMapper.selectUserList());
        LOG.debug("Result : {}",  userMapper.selectUserList());
        LOG.debug("Result : {}", userMapper.selectUserList());
    }


    @Test
    public void testNotEhcache() {

        /*不使用缓存*/

        LOG.debug("Result : {}",  userMapper.selectUserList());
        LOG.debug("Result : {}",  userMapper.selectUserList());
        LOG.debug("Result : {}", userMapper.selectUserList());
    }


    @Test
    public void testString() {
        String s1 = "Programming";             //F
        String s2 = new String("Programming");   //T
        String s3 = "Program" + "ming";                //T
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

    @SuppressWarnings("Since15")
    @Test
    public void testTime() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));    // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

        // Java 8
        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue());     // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
    }

    @SuppressWarnings("Since15")
    @Test
    public void dateFormat() {
        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date1 = new Date();
        System.out.println(oldFormatter.format(date1));     //2016/11/01 10:44:40

        // Java 8
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date2 = LocalDate.now();
        System.out.println(date2.format(newFormatter));    //2016/11/01
    }



    @Test
    public  void fetchZhihu() {

        List<String> pageList = new ArrayList<String>();
        for (int i = 1; i <= 50; i++) {
            pageList.add("https://www.zhihu.com/topic/19552430/top-answers?page=" + i);
        }
        for (int k = 1; k <= 50; k++) {
            String url = pageList.get(k-1);
            try {
                Document document= Jsoup.connect(url).get();
                //String html= document.html();
                //System.out.println("html: "+ html);
                Elements elements= document.select("a.zm-item-vote-count.js-expand.js-vote-count");
                List<String> collectList =new ArrayList<String>();
                for(int i = 0;i<elements.size();i++) {
                    String count = elements.get(i).text();
                    //System.out.println(count);
                    collectList.add(count);
                }
                //System.out.println(collectList.size());
                Elements elements2= document.select("a.question_link");
                //System.out.println(collectList.size()+"    "+elements2.size());
                for (int j = 0;j < elements2.size();j++) {
                    if(!elements2.get(j).attr("target").equals("_blank")) {
                        continue;
                    }
                    String href = elements2.get(j).attr("href");
                    String hrefStr = BASE_URL+href;
                    String question = elements2.get(j).text();
                    String upvote=collectList.get(j);
                    System.out.println("第"+k+"页" +"     " + hrefStr + "    "+question +"    "+upvote);
                    Record record = new Record();
                    record.setUrl(hrefStr);
                    record.setQuestion(question);
                    record.setUpvote(upvote);
                    recordService.insertSelective(record);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tianf on 2016/11/1.
 */
public class CrawlerTest {

    public static final Integer MAX_THREADS = 20;
    private static List<String> pageList = new ArrayList<String>();

    public static void main(String[] args) {
        for(int i = 1 ;i <= 50; i++ ) {
            pageList.add("https://www.zhihu.com/topic/19552430/top-answers?page="+i);
        }


        //ExecutorService service = Executors.newFixedThreadPool(50);
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 1; i <= 50; i++) {
            //System.out.println("第"+i+"页");
            Crawer crawer = new Crawer(pageList.get(i-1));
            service.execute(new CrawlerThread(crawer));
        }

    }


}

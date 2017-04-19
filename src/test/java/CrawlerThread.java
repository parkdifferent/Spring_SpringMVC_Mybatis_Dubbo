import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import com.szkingdom.ssm.service.impl.RecordServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianf on 2016/10/31.
 */
public class CrawlerThread implements Runnable  {


    private Lock crawlerLock = new ReentrantLock();
    private Crawer crawer;

    public CrawlerThread(Crawer crawer) {
        this.crawer = crawer;
    }

    public void run() {
        synchronized (crawer) {
            crawer.fetch();
        }
    }
}

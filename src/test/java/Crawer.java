import com.szkingdom.ssm.entity.Record;
import com.szkingdom.ssm.service.IRecordService;
import com.szkingdom.ssm.service.impl.RecordServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianf on 2016/11/1.
 */
public class Crawer {

    public static final String BASE_URL = "https://www.zhihu.com/";
    private String url;

    public Crawer(String url) {
        this.url = url;
    }


    public synchronized  void fetch() {
        //crawlerLock.lock();
        try {
            //Thread.sleep(5000);
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
                System.out.println("第"+"页" +"     " + hrefStr + "    "+question +"    "+upvote);
                IRecordService recordService = new RecordServiceImpl();
                Record record = new Record();
                record.setUrl(hrefStr);
                record.setQuestion(question);
                record.setUpvote(upvote);
                recordService.insertSelective(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (InterruptedException e) {
            e.printStackTrace();
        }*/ finally {
            //crawlerLock.unlock();
        }

    }
}

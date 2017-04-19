import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianf on 2016/10/31.
 */
public class JsoupTest {

    private static final String BASE_URL = "https://www.zhihu.com/";

    public static void main(String[] args) {
        //fetchTitle();
        //fetchImg();
        fetchZhihu();
    }
    public static void fetchTitle() {
        String url = "http://www.baidu.com";
        try {
            Document document= Jsoup.connect(url).get();
            String title= document.title();
            System.out.println("title: "+ title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fetchImg() {
        String url = "http://www.baidu.com";
        try {
            Document document= Jsoup.connect(url).get();
            Element img= document.select("img").first();
            String src = img.attr("src");
            System.out.println("src: "+ src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        List<String> pageList = new ArrayList<String>();
        for (int i = 1; i <= 50; i++) {
            pageList.add("https://www.zhihu.com/topic/19552430/top-answers?page=" + i);
        }
    }


    public static void fetchZhihu() {

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

              /*  Elements elements= document.select(".zm-item-vote-count.js-expand.js-vote-count");
                List<String> collectList =new ArrayList<String>();
                for(int i = 0;i<elements.size();i++) {
                    String count = elements.get(i).text();
                    //System.out.println(count);
                    collectList.add(count);
                }*/

                Elements elements2= document.select(".question_link");
                //System.out.println(elements.size()+"    "+elements2.size());
                for (int j = 0;j < elements2.size();j++) {
                    String href = elements2.get(j).attr("href");
                    String hrefStr = BASE_URL+href;
                    String question = elements2.get(j).text();
                    System.out.println("第"+k+"页" +"     " + hrefStr + "    "+question);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

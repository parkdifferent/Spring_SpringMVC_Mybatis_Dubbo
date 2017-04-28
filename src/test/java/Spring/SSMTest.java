package Spring;

import com.szkingdom.ssm.dao.AnswerMapper;
import com.szkingdom.ssm.dao.QuestionMapper;
import com.szkingdom.ssm.entity.Answer;
import com.szkingdom.ssm.entity.Question;
import com.szkingdom.ssm.entity.Tag;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/4/24.
 */


@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SSMTest {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerMapper answerMapper;

    @org.junit.Test
    public void test() {

        Question question = questionMapper.findOne((long) 1);
        System.out.println(question.toString());
    }

    @Test
    public void test1() {
        Question question = new Question();
        question.setTitle("hello");
        question.setDescription("world");
        question.setCreatedTime(new Date());
        questionMapper.create(question);
    }

    @Test
    public void test2() {
        Question question = questionMapper.findOne((long) 1);
        question.setTitle("HELLO");
        question.setDescription("WORLD");
        questionMapper.update(question);
    }

    @Test
    public void test3() {
        questionMapper.delete((long) 2);
    }

    @Test
    public void test4() {
        Answer answer = answerMapper.findOne((long) 1);
        System.out.println(answer.toString());
        //Question question = answer.getQuestion();
        //System.out.println(question.toString());
    }

    @Test
    public void test5() {
        Question question = questionMapper.findOne((long) 1);
        System.out.println(question.toString());
    }

    @Test
    public void test6() {
        List<Tag> tags = questionMapper.findTags((long) 1);
        System.out.println(tags.toString());

    }

















}

package jeebbs.restful;

import jeebbs.restful.hello.Greeting;
import jeebbs.restful.hello.GreetingMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/6/22 0022.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class GreetingTests {

    @Autowired
    private GreetingMapper greetingMapper;

    @Test
    @Rollback
    public void testGreetingMapper() throws Exception {

        //测试insert
        greetingMapper.insert("A测试");
        Greeting g = greetingMapper.findByContent("A测试");
        Assert.assertEquals("A测试", g.getContent());

        //测试update
        g.setContent("B测试");
        greetingMapper.update(g);
        g = greetingMapper.findById(g.getId());
        Assert.assertEquals("B测试", g.getContent());

        //测试delete
        greetingMapper.delete(g.getId());
        g = greetingMapper.findByContent("B测试");
        Assert.assertEquals(null, g);

        //测试insert对象插入
        g = new Greeting("C测试");
        greetingMapper.insertByGreeting(g);
        Assert.assertEquals("C测试", greetingMapper.findByContent("C测试").getContent());


        //测试insert map插入
        Map<String, Object> map = new HashMap<>();
        map.put("content", "D测试");
        greetingMapper.insertByMap(map);
        Assert.assertEquals("D测试", greetingMapper.findByContent("D测试").getContent());

        List<Greeting> gList = greetingMapper.findAll();
        for(Greeting gr: gList) {
            Assert.assertNotEquals(null, gr.getContent());
        }
    }

}

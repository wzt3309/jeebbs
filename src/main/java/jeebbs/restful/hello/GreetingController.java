package jeebbs.restful.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ztwang on 2017/6/22 0022.
 */
@RestController
public class GreetingController {
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingMapper greetingMapper;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        log.trace("test");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/greeting/find")
    public Greeting greetingFind(@RequestParam(value="name", defaultValue="test1") String name) {
        return greetingMapper.findByContent(name);
    }

    @RequestMapping("/greeting/findAll")
    public List<Greeting> greetingFindAll() {
        return greetingMapper.findAll();
    }
}

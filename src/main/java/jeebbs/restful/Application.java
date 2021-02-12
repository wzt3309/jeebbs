package jeebbs.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by ztwang on 2017/6/22 0022.
 */

@SpringBootApplication
@EnableScheduling
@EnableCaching //yth
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

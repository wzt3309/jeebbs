package jeebbs.restful.util.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 限制HTTP请求的间隔时间
 * Created by ztwang on 2017/6/23 0023.
 */
public class Throttle {
    private static final Logger LOG = LoggerFactory.getLogger(Throttle.class);

    private static final long DEFAULT_DELAY = 100;   //延迟100ms
    private AtomicLong delay;
    private Map<String, Date> domains = new HashMap<>();

    public Throttle() {
        this(DEFAULT_DELAY);
    }

    public Throttle(long delay) {
        this.delay = new AtomicLong(delay);
    }

    public void wait(String url) {
        String domain = null;
        try {
           URL tmp = new URL(url);
           String host = tmp.getHost();
           int port = tmp.getPort();
           domain = String.format("%s:%d", host, port);
        } catch (MalformedURLException e) {
           LOG.error(String.format("Can't parse url \'%s\'", url));
        }

        if (StringUtils.isEmpty(domain)) return;
        synchronized (this) {
            Date lastAccessed = domains.get(domain);
            if (delay.get() > 0 && lastAccessed != null) {
                long sleepMillis = delay.get() - millisHasGone(lastAccessed);
                while (sleepMillis > 0) {
                    try {
                        LOG.debug(String.format("\'%s\' waiting... %d ms", url, sleepMillis));
                        wait(sleepMillis);
                    } catch (InterruptedException e) {
                        LOG.warn("Interrupted has happened");
                    }
                    lastAccessed = domains.get(domain);
                    sleepMillis = delay.get() - millisHasGone(lastAccessed);
                }
            }
            domains.put(domain, new Date());
        }
    }

    public long millisHasGone(final Date lastAccessed) {
        long millis = System.currentTimeMillis() - lastAccessed.getTime();
        millis = millis > 0 ? millis : 0;
        return millis;
    }
}

package jeebbs.restful.hello;

/**
 * Created by ztwang on 2017/6/22 0022.
 */
public class Greeting {
    private Long id;
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package jeebbs.restful.service.news.model;

import java.sql.Timestamp;

/**
 * 财经新闻实体类
 * Created by ztwang on 2017/6/23 0023.
 */
public class News {
    private Long id;
    private String source;          //新闻来源
    private String title;           //新闻标题
    private String href;            //新闻内容链接
    private String profile;         //新闻摘要
    private Timestamp stmp;         //新闻发布时间

    public News() {
    }

    public News(String source, String title, String href, String profile, Timestamp stmp) {
        this.source = source;
        this.title = title;
        this.href = href;
        this.profile = profile;
        this.stmp = stmp;
    }

    public News(Long id, String source, String title, String href, String profile, Timestamp stmp) {
        this.id = id;
        this.source = source;
        this.title = title;
        this.href = href;
        this.profile = profile;
        this.stmp = stmp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Timestamp getStmp() {
        return stmp;
    }

    public void setStmp(Timestamp stmp) {
        this.stmp = stmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (source != null ? !source.equals(news.source) : news.source != null) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        return href != null ? href.equals(news.href) : news.href == null;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", profile='" + profile + '\'' +
                ", stmp=" + stmp +
                '}';
    }
}

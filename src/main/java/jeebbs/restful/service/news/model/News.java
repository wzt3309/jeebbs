package jeebbs.restful.service.news.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * 财经新闻实体类
 * Created by ztwang on 2017/6/23 0023.
 */
@ApiModel(description = "财经新闻实体类")
public class News {
    @ApiModelProperty(value = "标识符id", position = 1, example = "long")
    private Long id;
    @ApiModelProperty(value = "新闻来源", position = 2)
    private String source;          //新闻来源
    @ApiModelProperty(value = "新闻标题", position = 3)
    private String title;           //新闻标题
    @ApiModelProperty(value = "新闻内容链接", position = 4)
    private String href;            //新闻内容链接
    @ApiModelProperty(value = "新闻摘要", position = 5)
    private String profile;         //新闻摘要
    @ApiModelProperty(value = "新闻发布时间",
                      example = "yyyy-MM-dd HH:mm:ss",
                      position = 6)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
        return title != null ? title.equals(news.title) : news.title == null;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
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

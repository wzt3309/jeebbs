package jeebbs.restful.service.specialanalyse.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Date;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
public class SpecialAnalyse {
    private Long id;
    private Double radio1;
    private Double radio2;
    private Long radio3;
    private Date updateDate;

    public SpecialAnalyse() {
    }

    public SpecialAnalyse(Double radio1, Double radio2, Long radio3) {
        this(radio1, radio2, radio3, new Date(System.currentTimeMillis()));
    }

    public SpecialAnalyse(Double radio1, Double radio2, Long radio3, Date updateDate) {
        this.id = null;
        this.radio1 = radio1;
        this.radio2 = radio2;
        this.radio3 = radio3;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRadio1() {
        return radio1;
    }

    public void setRadio1(Double radio1) {
        this.radio1 = radio1;
    }

    public Double getRadio2() {
        return radio2;
    }

    public void setRadio2(Double radio2) {
        this.radio2 = radio2;
    }

    public Long getRadio3() {
        return radio3;
    }

    public void setRadio3(Long radio3) {
        this.radio3 = radio3;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "SpecialAnalyse{" +
                "id=" + id +
                ", radio1=" + radio1 +
                ", radio2=" + radio2 +
                ", radio3=" + radio3 +
                ", updateDate=" + updateDate +
                '}';
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,
                "radio1", "radio2", "radio3", "updateDate");
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that,
                "radio1", "radio2", "radio3", "updateDate");
    }
}

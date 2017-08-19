package jeebbs.restful.service.specialanalyse.model;

import java.sql.Date;

/**
 * Created by ztwang on 2017/8/19 0019.
 */
public class FinanceRadio {
    private Long id;
    private Long radio;
    private Date updateDate;

    public FinanceRadio() {
    }

    public FinanceRadio(Long radio) {
        this(radio, new Date(System.currentTimeMillis()));
    }

    public FinanceRadio(Long radio, Date updateDate) {
        this.id = null;
        this.radio = radio;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRadio() {
        return radio;
    }

    public void setRadio(Long radio1) {
        this.radio = radio1;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

package jeebbs.restful.service.specialanalyse.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * Created by ztwang on 2017/8/19 0019.
 */
public class FinanceRadio {
    @ApiModelProperty(value = "标识符id", position = 1, example = "Long")
    private Long id;
    @ApiModelProperty(value = "融资融券领先值", position = 2, example = "Double")
    private Long radio;
    @ApiModelProperty(value = "日期", example = "yyyy-MM-dd", position = 3)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

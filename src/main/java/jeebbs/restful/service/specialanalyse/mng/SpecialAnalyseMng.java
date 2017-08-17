package jeebbs.restful.service.specialanalyse.mng;

import jeebbs.restful.service.specialanalyse.model.SpecialAnalyse;
import jeebbs.restful.util.HttpUtil;
import jeebbs.restful.util.JacksonUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by ztwang on 2017/8/17 0017.
 */
@Service
public class SpecialAnalyseMng {
    private static final Logger logger = LoggerFactory.getLogger(SpecialAnalyse.class);
    private static final int DEFAULT_DAYS = 30;
    private static final String DATE_LIST_URL = "http://ddx.gubit.cn/xg/js/zhdatelist.js";
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SpecialAnalyseMapper mapper;

    @Autowired
    private StockRadioMng stockRadioMng;

    public void insert() {
        insert(null);
    }

    public void insert(Date date) {
        Double radio1 = getRadio1(date);
        Double radio2 = getRadio2(date);
        Double radio3 = getRadio3(date);
        Double radio4 = getRadio4(date);
        if (ObjectUtils.allNotNull(radio1, radio2, radio3, radio4)) {
            SpecialAnalyse bean = new SpecialAnalyse(radio1, radio2, radio3, radio4,
                    new java.sql.Date(date == null ? System.currentTimeMillis() : date.getTime()));
            mapper.insert(bean);
        }
    }

    public void check() {
        List<Date> dateList = getDateList();
        if (!CollectionUtils.isEmpty(dateList)) {
            for (Date date: dateList) {
                SpecialAnalyse bean = mapper.findByDate(new java.sql.Date(date.getTime()));
                if (bean == null) {
                    insert(date);
                }
            }
        }
    }

    public List<SpecialAnalyse> findAllByDate(Date from, Date to) {
        if (from == null || to == null) {
            return mapper.findListByDays(DEFAULT_DAYS);
        }
        return mapper.findListByDate(new java.sql.Date(from.getTime()),
                new java.sql.Date(to.getTime()));
    }

    private List<Date> getDateList() {
        String response = HttpUtil.sendGET(DATE_LIST_URL);
        List<Date> dateList = new ArrayList<>();
        if (!StringUtils.isEmpty(response)) {
            int beg = response.indexOf("[");
            int end = response.indexOf(";");
            if (beg <= end) {
                String dateListJson = response.substring(beg, end);
                List<String> list = JacksonUtil.jsonArray2PojoList(dateListJson, String.class);
                if (!CollectionUtils.isEmpty(list)) {
                    Collections.reverse(list);
                    list = list.subList(0, list.size() >= DEFAULT_DAYS ? DEFAULT_DAYS : list.size());
                    for (String str: list) {
                        try {
                            dateList.add(DATE_FMT.parse(str));
                        } catch (ParseException e) {
                            if (logger.isDebugEnabled()) {
                                logger.error(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        return dateList;
    }

    private double getRadio4(Date date) {
        return 0;
    }

    private double getRadio3(Date date) {
        return 0;
    }

    private double getRadio2(Date date) {
        return stockRadioMng.getRadio2(date);
    }

    private double getRadio1(Date date) {
        return stockRadioMng.getRadio1(date);
    }
}

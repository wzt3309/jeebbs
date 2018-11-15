package jeebbs.restful.service.stockanalyse.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jeebbs.restful.service.news.model.News;
import jeebbs.restful.service.stockanalyse.mng.StockAnalyseMng;
import jeebbs.restful.service.stockanalyse.model.FundFlow;
import jeebbs.restful.service.stockanalyse.model.StockAnalyse;
import jeebbs.restful.service.stockdata.mng.TradingMng;
import jeebbs.restful.service.stockdata.model.StockTrade;
import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by ztwang on 2017/8/22 0022.
 */
@Api(value = "API - StockController",
        description = "股票分析接口")
@RestController
@RequestMapping("/api/stockanalyse")
@CrossOrigin//yth
public class StockAnalyseController {
    private StockAnalyseMng stockAnalyseMng;

    @ApiOperation(value = "获取当日股票分析",
            notes = "获取当日股票分析，包含投资建议\n" +
                    "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `News`新闻数据的列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = StockAnalyse.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "进行排序的字段 " +
                    "可以选择的为code trade changepercent turnoverratio per main partIn",
                    dataType = "String", paramType = "query", defaultValue = "code"),
            @ApiImplicitParam(name = "desc", value = "是否为降序",
                    dataType = "int", paramType = "query", defaultValue = "-1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockAnalyse.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findStockAnalyse", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<StockAnalyse>> findStockAnalyse(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                   @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                                   @RequestParam(value = "desc", defaultValue = "1") int desc) {
        return ResponseUtil.success(stockAnalyseMng.findStockAnalyse(pageNum, pageSize, sort, desc));
    }

    @ApiOperation(value = "获取资金流向",
            notes = "获取特定时间段的资金流向，包含行业资金流和概念资金流向\n" +
                    "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `fund_flow_list`资金流列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = FundFlow.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "updateDateFrom", value = "资金流统计的开始日期，格式为 yyyy-MM-dd，默认值为空",
                    dataType = "Date", paramType = "query", defaultValue = "2018-11-01"),
            @ApiImplicitParam(name = "updateDateTo", value = "资金流统计的结束日期，格式为 yyyy-MM-dd，默认值为空",
                    dataType = "Date", paramType = "query", defaultValue = "2018-11-01"),
            @ApiImplicitParam(name = "type", value = "数据类型，行业或者概念，默认值为空",
                    dataType = "String", paramType = "query", defaultValue = "行业")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = FundFlow.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/fundFlowAnalyse", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<FundFlow>> fundFlowAnalyse(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                   @RequestParam(value = "updateDateFrom", defaultValue = "") String updateDateFrom,
                                                                   @RequestParam(value = "updateDateTo", defaultValue = "") String updateDateTo,
                                                                   @RequestParam(value = "type", defaultValue = "") String type) throws ParseException {


        //转换格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom= sf.parse(updateDateFrom);//Date形式
        Date dateTo= sf.parse(updateDateTo);//Date形式

        //判断数据类型输入是否正确
        if((!type.equals("行业"))&&(!type.equals("概念"))){
            return ResponseUtil.success(null);
        }

        //必须写在数据库查询前，不然会失效
        PageHelper.startPage(pageNum, pageSize);
        //数据库查询结果
        List<FundFlow> searchList = stockAnalyseMng.fundFlowAnalyse(type,dateFrom,dateTo);

        if (ObjectUtils.isEmpty(searchList)) return ResponseUtil.success(null);
        PageInfo<FundFlow> pageInfo = new PageInfo<>(searchList);
        return ResponseUtil.success(pageInfo);
    }

//    //测试接口函数
//    @RequestMapping(value = "/updateFundFlow", method = RequestMethod.GET)
//    public ResponseEntity<PageInfo<FundFlow>> updateFundFlow(){
//
//        stockAnalyseMng.updateFundFlow();
//        return ResponseUtil.success(null);
//
//    }


    @Autowired
    public void setStockAnalyseMng(StockAnalyseMng stockAnalyseMng) {
        this.stockAnalyseMng = stockAnalyseMng;
    }
}

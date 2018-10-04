package jeebbs.restful.service.stockdata.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jeebbs.restful.service.stockdata.mng.TradingMng;
import jeebbs.restful.service.stockdata.model.StockDaily;
import jeebbs.restful.service.stockdata.model.StockTop;
import jeebbs.restful.service.stockdata.model.StockTrade;
import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ztwang on 2017/8/6 0006.
 */
@Api(value = "API - StockController",
        description = "股票数据接口")
@RestController
@RequestMapping("/api/stock")
@CrossOrigin//yth
public class StockController {
    private static final String DATE_FORMAT = "\\d{4}-\\d{1,2}-\\d{1,2}";//日期格式
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_FORMAT);

    @ApiOperation(value = "获得某个股票历史日K线数据",
            notes = "返回从某个日期开始（包括），到某个日期结束（包含）的股票日K线数据\n" +
                    "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `StockDaily`股票日K线数据列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = StockDaily.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "股票代码",
                    dataType = "String", paramType = "query", example = "600848"),
            @ApiImplicitParam(name = "start", value = "起始日期",
                    dataType = "String", paramType = "query", example = "yyyy-MM-dd `2017-1-1`"),
            @ApiImplicitParam(name = "end", value = "结束日期",
                    dataType = "String", paramType = "query", example = "yyyy-MM-dd `2017-12-31`"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "进行排序的字段",
                    dataType = "String", paramType = "query", defaultValue = "date"),
            @ApiImplicitParam(name = "desc", value = "是否为降序",
                    dataType = "int", paramType = "query", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockDaily.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )

    @RequestMapping(value = "/getHistoryData", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<StockDaily>> getHistoryData(@RequestParam(value = "code", defaultValue = "") String code,
                                                               @RequestParam(value = "start", defaultValue = "") String start,
                                                               @RequestParam(value = "end", defaultValue = "") String end,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                               @RequestParam(value = "sort", defaultValue = "date") String sort,
                                                               @RequestParam(value = "desc", defaultValue = "1") int desc) {
        if (StringUtils.isBlank(code)) return ResponseUtil.success(null);
        if (!StringUtils.isBlank(start)) {
            Matcher m  =DATE_PATTERN.matcher(start);
            if (!m.find()) {
                throw new IllegalArgumentException("'start' format is invalid");
            }
        }
        if (!StringUtils.isBlank(end)) {
            Matcher m = DATE_PATTERN.matcher(end);
            if (!m.find()) {
                throw new IllegalArgumentException("'end' format is invalid");
            }
        }
        PageInfo<StockDaily> pageInfo = TradingMng.getHistoryData(code, start, end, pageNum, pageSize, sort, !(desc == 1));
        return ResponseUtil.success(pageInfo);
    }

    @ApiOperation(value = "获取当日股票交易数据",
            notes = "获取当日股票交易数据\n" +
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
            response = StockTrade.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "进行排序的字段",
                    dataType = "String", paramType = "query", defaultValue = "date"),
            @ApiImplicitParam(name = "desc", value = "是否为升序",
                    dataType = "int", paramType = "query", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockTrade.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/getTodayAll", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<StockTrade>> getTodayAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                            @RequestParam(value = "sort", defaultValue = "code") String sort,
                                                            @RequestParam(value = "desc", defaultValue = "1") int desc) {
        PageInfo<StockTrade> pageInfo = TradingMng.getTodayAll(pageNum, pageSize, sort, !(desc == 1));
        return ResponseUtil.success(pageInfo);
    }

    @ApiOperation(value = "查询股票代码",
            notes = "模糊查找股票代码",
            response = List.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 3)
    @ApiImplicitParam(name = "query", value = "查询字段(股票名首字母大写)",
            dataType = "String", paramType = "query", defaultValue = "DT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = List.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/searchCode", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, String>>> searchCode(@RequestParam(value = "query", defaultValue = "") String query) {
        List<Map<String, String>> searchList = TradingMng.searchCodeByName(query);
        return ResponseUtil.success(searchList);
    }

    @ApiOperation(value = "获取某日值得关注的股票",
            notes = "获取某日值得关注的股票",
            response = StockTop.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 4)
    @ApiImplicitParam(name = "date", value = "日期",
            dataType = "String", paramType = "query", example = "yyyy-MM-dd `2017-1-1`")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockTop.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/getTopList", method = RequestMethod.GET)
    public ResponseEntity<List<StockTop>> getTopList(@RequestParam(value = "date", defaultValue = "") String date,
                                                     @RequestParam(value = "trend", defaultValue = "LT") String trend) {
        if (!StringUtils.isBlank(date)) {
            Matcher m = DATE_PATTERN.matcher(date);
            if (!m.find()) {
                throw new IllegalArgumentException("'date' format is invalid");
            }
        }
        List<StockTop> topList = TradingMng.getTopList(date);

        List<StockTop> res = new ArrayList<>();
        if (topList != null) {
            if ("LT".equalsIgnoreCase(trend)) {
                res = topList.stream().filter(x -> x.getPchange().floatValue() > 0).collect(Collectors.toList());
            }
            if ("TT".equalsIgnoreCase(trend)) {
                res = topList.stream().filter(x -> x.getPchange().floatValue() < 0).collect(Collectors.toList());
            }
        }
        return ResponseUtil.success(res);
    }


}

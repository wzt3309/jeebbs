package jeebbs.restful.service.specialanalyse.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jeebbs.restful.service.specialanalyse.mng.SpecialAnalyseMng;
import jeebbs.restful.service.specialanalyse.model.FinanceRadio;
import jeebbs.restful.service.specialanalyse.model.StockRadio;
import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ztwang on 2017/8/20 0020.
 */
@Api(value = "API - SpecialAnalyseController",
        description = "特色分析接口")
@RestController
@RequestMapping("/api/specialanalyse")
@CrossOrigin//yth
public class SpecialAnalyseController {
    private SpecialAnalyseMng specialAnalyseMng;

    @ApiOperation(value = "根据日期获取StockRadio",
            notes = "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `StockRadio`列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = StockRadio.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 1)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "起始日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-1-1`", defaultValue = "2017-07-06"),
            @ApiImplicitParam(name = "to", value = "结束日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-12-31`", defaultValue = "2017-08-17"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "order", value = "记录排序方式",
                    dataType = "String", paramType = "query", defaultValue = "updateDate"),
            @ApiImplicitParam(name = "isDesc", value = "是否为降序 `1=true 0=false`",
                    dataType = "int", paramType = "query", defaultValue = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockRadio.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findStockRadioByDate", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<StockRadio>> findStockRadioByDate(@RequestParam(value = "from", defaultValue = "") String from,
                                                                     @RequestParam(value = "to", defaultValue = "") String to,
                                                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestParam(value = "order", defaultValue = "updateDate") String order,
                                                                     @RequestParam(value = "isDesc", defaultValue = "1") int isDesc) throws Exception {
        Date fromDate = null, toDate = null;
        if (!StringUtils.isAnyBlank(from, to)) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fromDate = fmt.parse(from);
            toDate = fmt.parse(to);
        }
        PageHelper.startPage(pageNum, pageSize, orderBy(order, isDesc));
        List<StockRadio> searchList = specialAnalyseMng.findStockRadioListByDate(fromDate, toDate);
        if (CollectionUtils.isEmpty(searchList)) {
            return ResponseUtil.success(null);
        }
        return ResponseUtil.success(new PageInfo<>(searchList));
    }

    @ApiOperation(value = "根据日期获取StockRadio",
            notes = "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `StockRadio`列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = StockRadio.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "days", value = "从今天开始n天内", dataType = "int",
                    paramType = "query", defaultValue = "30"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数",
                    dataType = "int", paramType = "query", defaultValue = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = StockRadio.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findStockRadioListByDays", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<StockRadio>> findStockRadioListByDays(@RequestParam(value = "days", defaultValue = "")int days,
                                                                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (days <= 0) {
            return ResponseUtil.success(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<StockRadio> searchList = specialAnalyseMng.findStockRadioListByDays(days);
        if (CollectionUtils.isEmpty(searchList)) {
            return ResponseUtil.success(null);
        }
        PageInfo<StockRadio> pageInfo = new PageInfo<>(searchList);
        pageInfo.setOrderBy("updateDate desc");
        return ResponseUtil.success(pageInfo);
    }

    @ApiOperation(value = "根据日期获取FinanceRadio",
            notes = "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `FinanceRadio`列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = FinanceRadio.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 3)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "起始日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-1-1`", defaultValue = "2017-07-06"),
            @ApiImplicitParam(name = "to", value = "结束日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-12-31`", defaultValue = "2017-08-17"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "order", value = "记录排序方式",
                    dataType = "String", paramType = "query", defaultValue = "updateDate"),
            @ApiImplicitParam(name = "isDesc", value = "是否为降序 `1=true 0=false`",
                    dataType = "int", paramType = "query", defaultValue = "1"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = FinanceRadio.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findFinanceRadioByDate", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<FinanceRadio>> findFinanceRadioByDate(@RequestParam(value = "from", defaultValue = "") String from,
                                                                     @RequestParam(value = "to", defaultValue = "") String to,
                                                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                     @RequestParam(value = "order", defaultValue = "updateDate") String order,
                                                                     @RequestParam(value = "isDesc", defaultValue = "1") int isDesc) throws Exception {
        Date fromDate = null, toDate = null;
        if (!StringUtils.isAnyBlank(from, to)) {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fromDate = fmt.parse(from);
            toDate = fmt.parse(to);
        }
        PageHelper.startPage(pageNum, pageSize, orderBy(order, isDesc));
        List<FinanceRadio> searchList = specialAnalyseMng.findFinanceRadioListByDate(fromDate, toDate);
        if (CollectionUtils.isEmpty(searchList)) {
            return ResponseUtil.success(null);
        }
        return ResponseUtil.success(new PageInfo<>(searchList));
    }

    @ApiOperation(value = "根据日期获取FinanceRadio",
            notes = "1. `pageNum` 当前页码\n" +
                    "2. `pageSize` 每页记录数\n" +
                    "3. `size` 当前页记录数\n" +
                    "4. `orderBy` 记录排序方式\n" +
                    "5. `startRow` 本页起始行位置\n" +
                    "6. `endRow` 本页结束行位置\n" +
                    "7. `total` 记录总数\n" +
                    "8. `pages` 页数\n" +
                    "9. `list` `FinanceRadio`列表 `[...]`\n" +
                    "10. `firstPage` 第一页的`pageNum`值\n" +
                    "11. `prePage` 上一页的`pageNum`值,为0则当前页为第一页\n" +
                    "12. `nextPage` 下一页的`pageNum`值,为0则当前页为最后一页\n" +
                    "13. `isFirstPage` 是否为首页\n" +
                    "14. `isLastPage` 是否为末页\n" +
                    "15. `hasPreviousPage` 是否有前页\n" +
                    "16. `hasNextPage` 是否有下一页\n" +
                    "17. `navigatePages` 分页器一次显示的页码数量\n" +
                    "18. `navigatepageNums` 列表，分页器的包含的页码`pageNum`\n",
            response = FinanceRadio.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "days", value = "从今天开始n天内", dataType = "int",
                    paramType = "query", defaultValue = "30"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数",
                    dataType = "int", paramType = "query", defaultValue = "10")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = FinanceRadio.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findFinanceRadioListByDays", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<FinanceRadio>> findFinanceRadioListByDays(@RequestParam(value = "days", defaultValue = "")int days,
                                                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (days <= 0) {
            return ResponseUtil.success(null);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<FinanceRadio> searchList = specialAnalyseMng.findFinanceRadioListByDays(days);
        if (CollectionUtils.isEmpty(searchList)) {
            return ResponseUtil.success(null);
        }
        PageInfo<FinanceRadio> pageInfo = new PageInfo<>(searchList);
        pageInfo.setOrderBy("updateDate desc");
        return ResponseUtil.success(pageInfo);
    }



    private String orderBy(String order, int isDesc) {
       return String.format("%s %s", order, isDesc == 1 ? "DESC" : "ASC");
    }

    @Autowired
    public void setSpecialAnalyseMng(SpecialAnalyseMng specialAnalyseMng) {
        this.specialAnalyseMng = specialAnalyseMng;
    }
}

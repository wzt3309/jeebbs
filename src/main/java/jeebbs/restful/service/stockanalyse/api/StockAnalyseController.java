package jeebbs.restful.service.stockanalyse.api;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jeebbs.restful.service.stockanalyse.mng.StockAnalyseMng;
import jeebbs.restful.service.stockanalyse.model.StockAnalyse;
import jeebbs.restful.service.stockdata.model.StockTrade;
import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @Autowired
    public void setStockAnalyseMng(StockAnalyseMng stockAnalyseMng) {
        this.stockAnalyseMng = stockAnalyseMng;
    }
}

package jeebbs.restful.service.news.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jeebbs.restful.service.news.mng.NewsMapper;
import jeebbs.restful.service.news.model.News;
import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ztwang on 2017/7/8 0008.
 */
@Api(value = "API - NewsController",
        description = "财经新闻模块接口详情")
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private static final String DATE_FORMAT = "\\d{4}-\\d{1,2}-\\d{1,2}";

    @Autowired
    private NewsMapper newsMapper;

    @ApiOperation(value = "根据id查找News对象",
                  notes = "`id`应该满足0&le;`id`，`id`如果为空，默认返回最新采集的财经新闻",
                  response = News.class,
                  produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
                  position = 1)
    @ApiImplicitParam(name = "id", value = "1. `id`应该满足1&le;`id`\n" +
                                           "2. `id`如果为空或`id`=-1，默认返回最新采集的财经新闻\n" +
                                           "3. `id`&lt;1(`id`!=-1)或`id`不存在，返回为空(`204`)",
                      dataType = "long", paramType = "query", defaultValue = "1")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = News.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<News> findById(@RequestParam(value="id", defaultValue="-1") Long id) {
        News res;
        if (id == -1) res = newsMapper.findAllByLimit(1).get(0);
        else res = newsMapper.findById(id);
        return ResponseUtil.success(res);
    }

    @ApiOperation(value = "根据title查找News对象",
            notes = "根据新闻标题查找新闻，采用精确匹配方式",
            response = News.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 2)
    @ApiImplicitParam(name = "title", value = "新闻标题，默认返回最新的新闻",
                      dataType = "String", paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = News.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findByTitle", method = RequestMethod.GET)
    public ResponseEntity<News> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
        News res;
        if (StringUtils.isBlank(title)) res = newsMapper.findAllByLimit(1).get(0);
        else res = newsMapper.findByTitle(title).get(0);
        return ResponseUtil.success(res);
    }

    @ApiOperation(value = "根据href查找News对象",
            notes = "根据新闻链接查找新闻，采用精确匹配方式",
            response = News.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 3)
    @ApiImplicitParam(name = "href", value = "新闻链接，默认返回最新的新闻",
            dataType = "String", paramType = "query")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = News.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findByHref", method = RequestMethod.GET)
    public ResponseEntity<News> findByHref(@RequestParam(value = "href", defaultValue = "") String href) {
        News res;
        if (StringUtils.isBlank(href)) res = newsMapper.findAllByLimit(1).get(0);
        else res = newsMapper.findByHref(href).get(0);
        return ResponseUtil.success(res);
    }

    @ApiOperation(value = "根据日期获取新闻列表",
            notes = "返回从某个日期开始（包含），到某个日期结束（包含）的新闻列表\n" +
                    "- 如果`from`为空，返回到`to`为止的新闻\n" +
                    "- 如果`to`为空，返回从`from`开始的新闻\n" +
                    "- 如果`from`和`to`都为空，则返回全部新闻\n" +
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
                    "17. `navigatePages` 同total\n" +
                    "18. `navigatepageNums` 列表，记录全部页码`pageNum`\n",
            response = PageInfo.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "from", value = "起始日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-1-1`"),
            @ApiImplicitParam(name = "to", value = "结束日期", dataType = "String",
                    paramType = "query", example = "yyyy-MM-dd `2017-12-31`"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "orderBy", value = "记录排序方式，默认值按时间降序(stmp desc)",
                    dataType = "String", paramType = "query", defaultValue = "stmp desc"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = PageInfo.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findByDate", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<News>> findByDate(@RequestParam(value = "from", defaultValue = "") String from,
                                                     @RequestParam(value = "to", defaultValue = "") String to,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                     @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                     @RequestParam(value = "orderBy", defaultValue = "stmp desc") String orderBy) {
        //必须写在数据库查询前，不然orderBy会失效
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<News> searchList = null;
        final Pattern p = Pattern.compile(DATE_FORMAT);
        Matcher mFrom, mTo;
        if (!StringUtils.isAnyBlank(from, to)) {
            mFrom= p.matcher(from);
            mTo = p.matcher(to);
            if (mFrom.find() && mTo.find()) {
                searchList = newsMapper.findByStmp(from, to);
            } else if (mFrom.find()) {
                searchList = newsMapper.findByStmpFrom(from);
            } else if (mTo.find()) {
                searchList = newsMapper.findByStmpTo(to);
            }
        }else if (!StringUtils.isBlank(from)) {
            mFrom = p.matcher(from);
            if (mFrom.find()) {
                searchList = newsMapper.findByStmpFrom(from);
            }
        }else if (!StringUtils.isBlank(to)) {
            mTo = p.matcher(to);
            if (mTo.find()) {
                searchList = newsMapper.findByStmpTo(to);
            }
        }else {
            searchList = newsMapper.findAll();
        }

        if (ObjectUtils.isEmpty(searchList)) return ResponseUtil.success(null);
        PageInfo<News> pageInfo = new PageInfo<>(searchList);
        return ResponseUtil.success(pageInfo);
    }

    @ApiOperation(value = "获取当天的新闻列表",
            notes = "**有副作用，如果在一天最早一次数据库更新之前使用，会返回空（还未有当天新闻产生）" +
                    " <font color=\"red\">建议: 只作为查询使用，而非展示页面</font>**\n" +
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
                    "17. `navigatePages` 同total\n" +
                    "18. `navigatepageNums` 列表，记录全部页码`pageNum`\n",
            response = PageInfo.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 5)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "orderBy", value = "记录排序方式，默认值按时间降序(stmp desc)",
                    dataType = "String", paramType = "query", defaultValue = "stmp desc"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = PageInfo.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findToday", method = RequestMethod.GET)
    // 有副作用，如果在一天最早一次数据库更新之前使用，会返回空（还未有当天新闻产生）
    public ResponseEntity<PageInfo<News>> findToday(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                    @RequestParam(value = "orderBy", defaultValue = "stmp desc") String orderBy) {
        //必须写在数据库查询前，不然orderBy会失效
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<News> searchList = newsMapper.findToday();
        if (ObjectUtils.isEmpty(searchList)) return ResponseUtil.success(null);
        PageInfo<News> pageInfo = new PageInfo<>(searchList);
        return ResponseUtil.success(pageInfo);
    }


    @ApiOperation(value = "获取新闻列表",
            notes = "使用分页查找方式获取新闻列表\n" +"1. `pageNum` 当前页码\n" +
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
                    "17. `navigatePages` 同total\n" +
                    "18. `navigatepageNums` 列表，记录全部页码`pageNum`\n",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 6)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "orderBy", value = "记录排序方式，默认值按时间降序(stmp desc)",
                    dataType = "String", paramType = "query", defaultValue = "stmp desc"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = PageInfo.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<News>> findAll(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                  @RequestParam(value = "orderBy", defaultValue = "stmp desc") String orderBy) {
        //必须写在数据库查询前，不然orderBy会失效
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<News> searchList;
        searchList = newsMapper.findAll();
        if (ObjectUtils.isEmpty(searchList)) return ResponseUtil.success(null);
        PageInfo<News> pageInfo = new PageInfo<>(searchList);
        return ResponseUtil.success(pageInfo);
    }

    @ApiOperation(value = "根据多参数查询新闻",
            notes = "使用id,source,title,href,profile,stmp参数查询新闻，参数个数可选，采用模糊匹配方式，" +
                    "如果不存在参数，则返回全部新闻\n" +
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
                    "17. `navigatePages` 同total\n" +
                    "18. `navigatepageNums` 列表，记录全部页码`pageNum`\n",
            response = PageInfo.class,
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE,
            position = 7)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params",allowEmptyValue = true, value = "**不需要填写**", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "新闻标识符",
                    dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "source", value = "新闻来源",
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "新闻标题",
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "href", value = "新闻链接",
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "profile", value = "新闻摘要",
                    dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "stmp", value = "新闻发布时间",
                    dataType = "Date", paramType = "query", example = "yyyy-MM-dd 2017-1-1"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码，从1开始；默认值为1",
                    dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的记录数；默认值为10",
                    dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "orderBy", value = "记录排序方式，默认值按时间降序(stmp desc)",
                    dataType = "String", paramType = "query", defaultValue = "stmp desc"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成", response = PageInfo.class),
            @ApiResponse(code = 204, message = "请求已完成，但数据为空", response = HashMap.class),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 401, message = "服务器收到请求但拒绝提供服务", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 403, message = "未授权客户机访问数据", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源", response = CustomerErrorAttributes.class),
            @ApiResponse(code = 500, message = "服务器不能完成请求", response = CustomerErrorAttributes.class)}
    )
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<PageInfo<News>> search(@RequestParam Map<String, String> params,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "orderBy", defaultValue = "stmp desc") String orderBy) {
        Map<String, String> queryMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(params)) {
            for (Map.Entry<String, String> entry: params.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue();
                if (!StringUtils.isAnyBlank(name, value)) {
                    if (StringUtils.equalsAnyIgnoreCase(name,
                            "id", "source", "title",
                            "href", "profile", "stmp")) {
                        queryMap.put(name, "%" + value + "%");
                    }
                }
            }
        }
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<News> searchList = newsMapper.findBySearchMap(queryMap);
        if (ObjectUtils.isEmpty(searchList)) return ResponseUtil.success(null);
        PageInfo<News> pageInfo = new PageInfo<>(searchList);
        return ResponseUtil.success(pageInfo);
    }

}

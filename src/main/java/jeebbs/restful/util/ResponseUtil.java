package jeebbs.restful.util;

import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public final class ResponseUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ResponseUtil.class);
    /**
     * 返回的代码列表
     */
    private static final String SUCCESS = "success";
    private static final String NO_CONTENT = "no_content";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private ResponseUtil() {

    }

    public static <T> ResponseEntity<T> success(T data) {
        if (ObjectUtils.isEmpty(data)) {
            return noContent(data);
        }
        return new ResponseEntity<>(data, headers(SUCCESS), HttpStatus.OK);
    }

    public static ResponseEntity<CustomerErrorAttributes> badRequest(String error,
                                                                     HttpServletRequest request,
                                                                     boolean includeStackTrace) {
        CustomerErrorAttributes errorAttrs = new CustomerErrorAttributes(HttpStatus.BAD_REQUEST.value());
        errorAttrs.init(new ServletRequestAttributes(request), includeStackTrace);
        if (!StringUtils.isEmpty(error))
            errorAttrs.setError(error);
        return new ResponseEntity<>(errorAttrs, headers(error), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomerErrorAttributes> notFound(String error,
                                                                   HttpServletRequest request,
                                                                   boolean includeStackTrace) {
        CustomerErrorAttributes errorAttrs = new CustomerErrorAttributes(HttpStatus.NOT_FOUND.value());
        errorAttrs.init(new ServletRequestAttributes(request), includeStackTrace);
        if (!StringUtils.isEmpty(error))
            errorAttrs.setError(error);
        return new ResponseEntity<>(errorAttrs, headers(error), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomerErrorAttributes> internalServerError(String error,
                                                                              HttpServletRequest request,
                                                                              boolean includeStackTrace) {
        CustomerErrorAttributes errorAttrs = new CustomerErrorAttributes(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorAttrs.init(new ServletRequestAttributes(request), includeStackTrace);
        if (!StringUtils.isEmpty(error))
            errorAttrs.setError(error);
        return new ResponseEntity<>(errorAttrs, headers(error), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<CustomerErrorAttributes> fail(HttpServletRequest request,
                                                                     boolean includeStackTrace) {
        CustomerErrorAttributes errorAttrs = new CustomerErrorAttributes();
        errorAttrs.init(new ServletRequestAttributes(request), includeStackTrace);
        HttpStatus status = errorAttrs.changeToHttpStatus();
        status = status != null ? status : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(errorAttrs, headers(errorAttrs.getError()), status);
    }


    public static <T> ResponseEntity<T> noContent(T data) {
        return new ResponseEntity<>(data, headers(NO_CONTENT), HttpStatus.NO_CONTENT);
    }

    private static MultiValueMap<String, String> headers(String msg) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Message", msg);
        headers.add("Time", SIMPLE_DATE_FORMAT.format(new Date()));
        return headers;
    }

}

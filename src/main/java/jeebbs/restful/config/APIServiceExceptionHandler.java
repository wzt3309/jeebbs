package jeebbs.restful.config;

import jeebbs.restful.util.ResponseUtil;
import jeebbs.restful.util.model.CustomerErrorAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
@EnableWebMvc
@RestControllerAdvice(basePackages = {"jeebbs.restful.service", "jeebbs.restful.hello"})
public class APIServiceExceptionHandler extends SimpleMappingExceptionResolver{
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<CustomerErrorAttributes> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
        log(req, ex);
        return ResponseUtil.notFound(null, req, LOGGER.isTraceEnabled());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomerErrorAttributes> constraintViolationException(HttpServletRequest req, ConstraintViolationException ex) {
        log(req, ex);
        return ResponseUtil.badRequest(null, req, LOGGER.isTraceEnabled());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CustomerErrorAttributes> defaultErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        log(req, ex);
        return ResponseUtil.internalServerError(null, req, LOGGER.isTraceEnabled());
    }

    private void log(HttpServletRequest req, Exception ex) {
        LOGGER.error(String.format("User: %s, request: [%s %s], msg: %s",
                req.getRemoteAddr(), req.getMethod(), req.getRequestURL(), ex.getMessage()));
    }
}

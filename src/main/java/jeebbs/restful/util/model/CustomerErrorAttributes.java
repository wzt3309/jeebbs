package jeebbs.restful.util.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ztwang on 2017/7/10 0010.
 */
@ApiModel(description = "封装请求错误信息")
public class CustomerErrorAttributes {
    private static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";
    private static final String PATH_ATTRIBUTE = HandlerMapping.class.getName() + ".pathWithinHandlerMapping";
    @ApiModelProperty(value = "错误状态码", readOnly = true, required = true, example = "int", position = 1)
    private Integer status;
    @ApiModelProperty(value = "错误原因", readOnly = true, required = true, position = 2)
    private String error;
    @ApiModelProperty(value = "URL请求路径", readOnly = true, required = true, position = 3)
    private String path;
    @ApiModelProperty(value = "根异常的类名", readOnly = true, position = 4)
    private String exception;
    @ApiModelProperty(value = "根异常的信息", readOnly = true, example = "String", position = 5)
    private Object message;
    @ApiModelProperty(value = "任何来自BindingResult异常的ObjectErrors",
                      readOnly = true, example = "String", reference = "List", position = 6)
    private List<ObjectError> errors;
    @ApiModelProperty(value = "异常的堆栈信息", readOnly = true, example = "String", position = 7)
    private String trace;
    @ApiModelProperty(value = "时间戳", readOnly = true, example = "yyyy-MM-dd HH:mm:ss", position = 8)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;

    public CustomerErrorAttributes() {
    }

    public CustomerErrorAttributes(Integer status) {
        this.status = status;
    }

    public void init(RequestAttributes requestAttributes,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        timestamp = new Date();
        errorAttributes.put("timestamp", timestamp);
        this.addStatus(errorAttributes, requestAttributes);
        this.addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
        this.addPath(errorAttributes, requestAttributes);
    }

    public Throwable getError(RequestAttributes requestAttributes) {
        Throwable exception = (Throwable) this.getAttribute(requestAttributes, ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = (Throwable)this.getAttribute(requestAttributes,
                                              "javax.servlet.error.exception");
        }
        return exception;
    }

    private void addStatus(Map<String, Object> errorAttributes,
                           RequestAttributes requestAttributes) {
        if (status == null)
            status = (Integer)this.getAttribute(requestAttributes,
                                                    "javax.servlet.error.status_code");
        if (status == null) {
            status = 999;
            error = "None";
            errorAttributes.put("status", status);
            errorAttributes.put("error", error);
        } else {
            errorAttributes.put("status", status);

            try {
                error = HttpStatus.valueOf(status).getReasonPhrase();
                errorAttributes.put("error", error);
            } catch (Exception e) {
                error = "Http Status" + status;
                errorAttributes.put("error", error);
            }
        }
    }

    private void addErrorDetails(Map<String, Object> errorAttributes,
                                 RequestAttributes requestAttributes,
                                 boolean includeStackTrace) {
        Throwable error = this.getError(requestAttributes);
        if (error != null) {
            while (true) {
                if (!(error instanceof ServletException) || error.getCause() == null) {
                    exception = error.getClass().getName();
                    errorAttributes.put("exception", exception);
                    this.addErrorMessage(errorAttributes, error);
                    if (includeStackTrace) {
                        this.addStackTrace(errorAttributes, error);
                    }
                    break;
                }

                error = ((ServletException)error).getCause();
            }
        }

        Object message = this.getAttribute(requestAttributes, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(message) || errorAttributes.get("message") == null)
                && !(error instanceof BindingResult)) {
            this.message = StringUtils.isEmpty(message)?"No message available" : message;
            errorAttributes.put("message", this.message);
        }
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
        BindingResult result = this.extractBindingResult(error);
        if(result == null) {
            message = error.getMessage();
            errorAttributes.put("message", message);
        } else {
            if(result.getErrorCount() > 0) {
                errors = result.getAllErrors();
                message = "Validation failed for object='" +
                          result.getObjectName() +
                          "'. Error count: " +
                          result.getErrorCount();

                errorAttributes.put("errors", errors);
                errorAttributes.put("message", message);
            } else {
                message = "No errors";
                errorAttributes.put("message", message);
            }

        }
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        trace = stackTrace.toString();
        errorAttributes.put("trace", trace);
    }

    private void addPath(Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        path = (String)this.getAttribute(requestAttributes, "javax.servlet.error.request_uri");
        if (path == null) {
            path = (String)this.getAttribute(requestAttributes, PATH_ATTRIBUTE);
        }
        if(path != null) {
            errorAttributes.put("path", path);
        }

    }

    private BindingResult extractBindingResult(Throwable error) {
        return error instanceof BindingResult?(BindingResult)error:(error instanceof MethodArgumentNotValidException ?((MethodArgumentNotValidException)error).getBindingResult():null);
    }

    private Object getAttribute(RequestAttributes requestAttributes, String name) {
        return requestAttributes.getAttribute(name, 0);
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public Object getMessage() {
        return message;
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public String getTrace() {
        return trace;
    }

    public String getPath() {
        return path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public HttpStatus changeToHttpStatus() {
        try {
            return HttpStatus.valueOf(status);
        }catch (Exception e) {
            return null;
        }
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

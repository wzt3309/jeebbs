package jeebbs.restful.util.model;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public class Response<T> {
    protected final int code;
    protected final String message;
    protected final T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

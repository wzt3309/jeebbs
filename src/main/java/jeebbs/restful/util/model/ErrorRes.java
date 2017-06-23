package jeebbs.restful.util.model;

/**
 * Created by ztwang on 2017/6/23 0023.
 */
public class ErrorRes {
    private final int code;
    private final String errMsg;

    public ErrorRes(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public String toString() {
        return "ErrorRes{" +
                "code=" + code +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

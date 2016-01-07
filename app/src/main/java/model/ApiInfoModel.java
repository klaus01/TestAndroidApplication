package model;

/**
 * Created by kelei on 15/12/31.
 */
public class ApiInfoModel<T> {
    public boolean isSuc;
    public String message;
    public T result;

    @Override
    public String toString() {
        super.toString();
        return "ApiInfoModel{" +
                "isSuc=" + isSuc +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}

package model;

/**
 * API返回的根对象
 * @param <T> result对象类型
 */
public class ApiInfoModel<T> {
    public boolean isSuc;
    public String message;
    public T result;

    @Override
    public String toString() {
        return "ApiInfoModel{" +
                "isSuc=" + isSuc +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}

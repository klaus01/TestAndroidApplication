package helper.api;

import android.util.Log;

import cz.msebera.android.httpclient.Header;

import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;
import com.google.gson.Gson;

import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import main.MyApplication;
import model.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * API请求对象
 * @param <T> API返回的result对象类型
 */
public class ApiHelper<T> {

    public static abstract class ApiComplete<T> {
        public abstract void onComplete(int statusCode, ApiInfoModel<T> apiInfo);
    }

    private static final String BASE_URL = "http://www.lawyer-center.com/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(RecommendModel.RecommendType.class, new RecommendModel.RecommendTypeDeserializer())
            .create();

    /**
     * GET请求
     * @param url 接口相对路径
     * @param params 接口参数
     * @param apiObjectType 返回对象的类型
     * @param completeHandler 完成回调
     */
    public void get(String url, RequestParams params, final Type apiObjectType, final ApiComplete<T> completeHandler) {
        client.get(getAbsoluteUrl(url), params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("GET请求失败", statusCode + "");
                completeHandler.onComplete(statusCode, null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ApiInfoModel<T> apiInfo = gson.fromJson(responseString, apiObjectType);
                Log.i("GET请求成功", apiInfo.toString());
                completeHandler.onComplete(statusCode, apiInfo);
            }
        });
    }

    public void post(String url, JSONObject params, final Type apiObjectType, final ApiComplete<T> completeHandler) {
        String jsonString = params == null ? null : params.toString();
        Log.i("POST PARAMS", jsonString);
        ByteArrayEntity entity = null;
        if (jsonString != null) {
            try {
                entity = new ByteArrayEntity(jsonString.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        client.post(MyApplication.getInstance(), getAbsoluteUrl(url), entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("POST请求失败", statusCode + "");
                completeHandler.onComplete(statusCode, null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ApiInfoModel<T> apiInfo = gson.fromJson(responseString, apiObjectType);
                Log.i("POST请求成功", apiInfo.toString());
                completeHandler.onComplete(statusCode, apiInfo);
            }
        });
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}

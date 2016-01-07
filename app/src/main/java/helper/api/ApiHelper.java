/**
 * Created by kelei on 15/12/31.
 */
package helper.api;

import android.util.Log;

import cz.msebera.android.httpclient.Header;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.*;
import com.google.gson.Gson;
import model.*;

import java.lang.reflect.Type;

public class ApiHelper<T> {

    public static abstract class ApiComplete<T> {
        public abstract void onComplete(int statusCode, ApiInfoModel<T> apiInfo);
    }

    private static final String BASE_URL = "http://www.lawyer-center.com/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(RecommendModel.RecommendType.class, new RecommendModel.RecommendTypeDeserializer())
            .create();

    public void get(String url, RequestParams params, final ApiComplete<T> completeHandler) {
        client.get(getAbsoluteUrl(url), params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.i("API请求失败", statusCode + "");
                completeHandler.onComplete(statusCode, null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type type = new TypeToken<ApiInfoModel<T>>() {}.getType();
                ApiInfoModel<T> apiInfo = gson.fromJson(responseString, type);
                Log.i("API请求成功", apiInfo.toString());
                completeHandler.onComplete(statusCode, apiInfo);
            }
        });
    }

    public void post(String url, RequestParams params, final ApiComplete<T> completeHandler) {
        client.post(getAbsoluteUrl(url), params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                completeHandler.onComplete(statusCode, null);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Type type = new TypeToken<ApiInfoModel<T>>() {}.getType();
                ApiInfoModel<T> apiInfo = gson.fromJson(responseString, type);
                completeHandler.onComplete(statusCode, apiInfo);
            }
        });
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}

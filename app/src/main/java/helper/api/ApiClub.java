package helper.api;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import model.*;

/**
 * 会馆相关接口
 */
public class ApiClub {

    /**
     * 会馆列表
     * @param cityId 城市ID
     * @param lat 经度
     * @param lng 纬度
     * @param pageIndex 第几页
     * @param pageSize 返回条数
     * @param completeHandler 回调
     */
    public static void getList(String cityId, float lat, float lng, int pageIndex, int pageSize, final ApiHelper.ApiComplete<ApiPagingModel<ClubModel>> completeHandler) {

        JSONObject queryJson = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            queryJson.put("pageIndex", pageIndex);
            queryJson.put("pageSize", pageSize);
            queryJson.put("cityId", cityId);
            queryJson.put("lat", lat);
            queryJson.put("lng", lng);
            jsonObject.put("query", queryJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new ApiHelper<ApiPagingModel<ClubModel>>().post(
                "club/getclublist",
                jsonObject,
                new TypeToken<ApiInfoModel<ApiPagingModel<ClubModel>>>() {
                }.getType(),
                completeHandler);
    }

    /**
     * 会馆详情
     * @param clubId 会馆ID
     * @param completeHandler 回调
     */
    public static void getInfo(int clubId, final ApiHelper.ApiComplete<ClubModel> completeHandler) {
        new ApiHelper<ClubModel>().get(
                "club/getclubinfo/" + clubId,
                null,
                new TypeToken<ApiInfoModel<ClubModel>>() {
                }.getType(),
                completeHandler);
    }

}

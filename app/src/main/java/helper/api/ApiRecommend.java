package helper.api;

import com.google.gson.reflect.TypeToken;

import model.*;

/**
 * 推荐相关接口
 */
public class ApiRecommend {

    /**
     * 推荐广告列表
     * @param pageIndex 获取第几页的数据，从1开始
     * @param pageSize  最多返回多少条数据
     * @param completeHandler 回调
     */
    public static void getList(int pageIndex, int pageSize, final ApiHelper.ApiComplete<ApiPagingModel<RecommendModel>> completeHandler) {
        new ApiHelper<ApiPagingModel<RecommendModel>>().get(
                "recommend/list/" + pageIndex + "/" + pageSize,
                null,
                new TypeToken<ApiInfoModel<ApiPagingModel<RecommendModel>>>(){}.getType(),
                completeHandler);
    }
}

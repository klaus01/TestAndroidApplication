package helper.api;

import model.*;

/**
 * Created by kelei on 16/1/7.
 */
public class ApiRecommend {

    /**
     * 推荐广告列表
     * @param pageIndex 获取第几页的数据，从1开始
     * @param pageSize  最多返回多少条数据
     * @param completeHandler 回调
     */
    public static void getList(int pageIndex, int pageSize, final ApiHelper.ApiComplete<ApiPagingModel<RecommendModel>> completeHandler) {
        ApiHelper api = new ApiHelper<ApiPagingModel<RecommendModel>>();
        api.get("recommend/list/" + pageIndex + "/" + pageSize, null, completeHandler);
    }
}

package model;

import com.google.gson.*;
import com.activeandroid.Model;
import com.activeandroid.annotation.*;

/**
 * 推荐广告模型
 */
@Table(name = "Recommend")
public class RecommendModel extends Model {
    /**
     * 推荐ID
     */
    @Column(name = "RecommendId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int RecommendId;
    /**
     * 类型
     */
    @Column(name = "Type")
    public RecommendType Type;
    /**
     * 图片链接地址
     */
    @Column(name = "Img")
    public String Img;


    /**
     * 推荐广告类型
     */
    public enum RecommendType {
        RecommendTypeNone,
        /**
         * 咨询问题
         */
        RecommendTypeQuestion,
        /**
         * 约见
         */
        RecommendTypeAppointment,
        /// 律师
        RecommendTypeLawyer,
        /// 无链接
        RecommendTypeNoUrl,
        /// 有链接
        RecommendTypeUrl,
        /// 会馆
        RecommendTypeClub,
        /// 新闻资讯文章
        RecommendTypeArticle,
    }

    public static class RecommendTypeDeserializer implements JsonSerializer<RecommendType>,
            JsonDeserializer<RecommendType> {

        @Override
        public JsonElement serialize(RecommendType state, java.lang.reflect.Type arg1,
                                     JsonSerializationContext arg2) {
            return new JsonPrimitive(state.ordinal());
        }

        @Override
        public RecommendType deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {
            if (json.getAsInt() < RecommendType.values().length)
                return RecommendType.values()[json.getAsInt()];
            return null;
        }

    }

    @Override
    public String toString() {
        return "RecommendModel{" +
                "RecommendId=" + RecommendId +
                ", Type=" + Type +
                ", Img='" + Img + '\'' +
                '}';
    }
}

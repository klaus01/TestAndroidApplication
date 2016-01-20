package model;

import com.activeandroid.Model;
import com.activeandroid.annotation.*;

/**
 * 会馆
 */
@Table(name = "Club")
public class ClubModel extends Model {
    /// 编号
    @Column(name = "ClubId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int ClubId;
    /// 创建时间
    @Column(name = "CreateDate")
    public String CreateDate;
    /// logo图片链接地址
    @Column(name = "Logo")
    public String Logo;
    /// banner图片链接地址
    @Column(name = "Banner")
    public String Banner;
    /// 名称
    @Column(name = "Name")
    public String Name;
    /// 介绍
    @Column(name = "Content")
    public String Content;
    /// 联系电话
    @Column(name = "Phone")
    public String Phone;
    /// 省名称
    @Column(name = "Province")
    public String Province;
    /// 省编号
    @Column(name = "ProvinceId")
    public String ProvinceId;
    /// 市名称
    @Column(name = "City")
    public String City;
    /// 市编号
    @Column(name = "CityId")
    public String CityId;
    /// 地址
    @Column(name = "Address")
    public String Address;
    /// 纬度
    @Column(name = "Lat")
    public float Lat;
    /// 经度
    @Column(name = "Lng")
    public float Lng;
    /// 距离
    @Column(name = "Distance")
    public float Distance;

    @Override
    public String toString() {
        return "ClubModel{" +
                "ClubId=" + ClubId +
                ", CreateDate='" + CreateDate + '\'' +
                ", Logo='" + Logo + '\'' +
                ", Banner='" + Banner + '\'' +
                ", Name='" + Name + '\'' +
                ", Content='" + Content + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Province='" + Province + '\'' +
                ", ProvinceId='" + ProvinceId + '\'' +
                ", City='" + City + '\'' +
                ", CityId='" + CityId + '\'' +
                ", Address='" + Address + '\'' +
                ", Lat=" + Lat +
                ", Lng=" + Lng +
                ", Distance=" + Distance +
                '}';
    }
}

package com.example.colordetails.repository;
//商品信息
import com.example.colordetails.entity.CommodityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface CommodityRepository extends JpaRepository<CommodityInfo,Integer> {
    //查询商品
    @Query(value = "SELECT * FROM commodity_info where state=1",nativeQuery = true)
    List<CommodityInfo> findAllCommodity();
//   根据商品id 查询单条商品信息
    @Query(value = "SELECT * FROM commodity_info WHERE id=?1",nativeQuery = true)
    CommodityInfo findByCommodityId(Integer id);

    //    根据Brend_id查询商品信息
    @Query(value = "SELECT * FROM commodity_info b where b.brand_info_id=?1",nativeQuery = true)
    List<CommodityInfo> findCommodityByBrandId(Integer id);

    //根据商品id上架商品
    @Modifying
    @Query(value = "UPDATE commodity_info b SET b.state=1 WHERE b.id=?1 ",nativeQuery = true)
    void upCommodityById(Integer id);

    //根据商品id下架商品
    @Modifying
    @Query(value = "UPDATE commodity_info b SET b.state=0 WHERE b.id=?1",nativeQuery = true)
    void downCommodityById(Integer id);

//    测试按照类型查找
//    @Query("SELECT  c from CommodityInfo c where c.waterTypeInfo.Name='矿泉水' ")
//    List<CommodityInfo> testFind();
}

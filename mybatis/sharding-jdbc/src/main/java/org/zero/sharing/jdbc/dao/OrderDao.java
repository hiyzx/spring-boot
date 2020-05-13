package org.zero.sharing.jdbc.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 水寒
 * @date 2020/5/12
 */
@Mapper
@Component
public interface OrderDao {

    /**
     * 新增订单
     *
     * @param price
     *            订单价格
     * @param userId
     *            用户id
     * @param status
     *            订单状态
     */
    @Insert("insert into t_order(price,user_id,status) value(#{price},#{userId},#{status})")
    int insertOrder(@Param("price") BigDecimal price, @Param("userId") Long userId, @Param("status") boolean status);

    /**
     * 根据id列表查询多个订单
     *
     * @param userIds
     *           用户id列表
     */
    @Select({ "<script>" + "select " + "*" + " from t_order t" + " where t.user_id in "
            + "<foreach collection='orderIds' item='id' open='(' separator=',' close=')'>" + " #{id} " + "</foreach>"
            + "</script>" })
    List<Map> selectOrderByIds(@Param("orderIds") List<Long> userIds);
}

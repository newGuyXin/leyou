package com.leyou.item.mapper;


import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @version V0.1
 * @项目名称：leyou
 * @类名：BrandMapper
 * @类描述：
 * @创建人：liujiaxin
 * @创建时间：2021/1/18 17:35
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * @类描述: 添加品牌和种类对应关系
     * @创建人: axin
     * @日期: 2021/11/18
     */
    @Insert("insert into tb_category_brand(category_id, brand_id) values (#{cid},#{id}) ")
    void insertBrandAndCategory(@Param("cid") Long cid, @Param("id") Long id);
}

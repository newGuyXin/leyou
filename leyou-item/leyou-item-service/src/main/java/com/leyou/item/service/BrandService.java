package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;

/**
 * @version V0.1
 * @项目名称：leyou
 * @类名：BrandService
 * @类描述：
 * @创建人：liujiaxin
 * @创建时间：2021/1/19 10:23
 */
@Transactional
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @类描述: 分页查询品牌
     * @创建人: axin
     * @日期: 2021/7/25
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows,
                                               String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        //添加分页查询条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);

        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * @类描述: 新增品牌
     * @创建人: axin
     * @日期: 2021/11/18
     */
    public void saveBrand(Brand brand, List<Long> cids) {
        brandMapper.insertSelective(brand);
        System.out.println(brand.getId());
        cids.forEach(cid ->{
            brandMapper.insertBrandAndCategory(cid,brand.getId());
        });

    }
}

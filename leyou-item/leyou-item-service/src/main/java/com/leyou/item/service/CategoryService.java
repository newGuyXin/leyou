package com.leyou.item.service;

import com.leyou.item.pojo.Category;
import com.leyou.item.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V0.1
 * @项目名称：leyou
 * @类名：CategoryService
 * @类描述：
 * @创建人：liujiaxin
 * @创建时间：2021/1/13 16:13
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @description：根据父节点查询子节点
     * @author：liujiaxin
     * @date：2021/1/13 17:20
     */
    public List<Category> queryCategoriesById(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);

    }
}

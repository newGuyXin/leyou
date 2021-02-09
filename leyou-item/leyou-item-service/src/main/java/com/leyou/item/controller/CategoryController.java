package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @version V0.1
 * @项目名称：leyou
 * @类名：CategoryController
 * @类描述：
 * @创建人：liujiaxin
 * @创建时间：2021/1/13 16:12
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @description：根据父节点的id查询子节点
     * @author：liujiaxin
     * @date：2021/1/13 17:32
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesById(@RequestParam(value = "pid", defaultValue = "0") Long pid) {
        //判断传入参数是否合法,400
        if (pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }

        //查询种类集合
        List<Category> categories = categoryService.queryCategoriesById(pid);
        //判断集合是否为空,404
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        //查询成功 200
        return ResponseEntity.ok(categories);
    }
}

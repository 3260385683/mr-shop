package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
/**
 * @ClassName CategoryServiceImpl
 * @Description: TODO
 * @Author caoyaohui
 * @Date 2020/8/27
 * @Version V1.0
 **/
@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Result<List<CategoryEntity>> getCategoryByPid(Integer pid) {

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setParentId(pid);

        List<CategoryEntity> list = categoryMapper.select(categoryEntity);

        return this.setResultSuccess(list);
    }

    @Transactional
    @Override
    public Result<JSONObject> saveCategory(CategoryEntity entity) {

        CategoryEntity parentCateEntity = new CategoryEntity();
        parentCateEntity.setId(entity.getParentId());
        parentCateEntity.setIsParent(1);
        categoryMapper.updateByPrimaryKeySelective(parentCateEntity);

        categoryMapper.insertSelective(entity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> editCategory(CategoryEntity entity) {
        categoryMapper.updateByPrimaryKeySelective(entity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> deleteCategory(Integer id) {
        CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);
        if(categoryEntity == null){
            return this.setResultError("当前id不存在");
        }

        if(categoryEntity.getIsParent() == 1){
            return this.setResultError("当前节点是父节点不能被删除");
        }

        Example example = new Example(CategoryEntity.class);

        example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());

        List<CategoryEntity> list = categoryMapper.selectByExample(example);
        if(list.size()==1){
            CategoryEntity parentCateEntity = new CategoryEntity();
            parentCateEntity.setId(categoryEntity.getParentId());
            parentCateEntity.setIsParent(0);
            categoryMapper.updateByPrimaryKeySelective(parentCateEntity);
        }

        categoryMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}

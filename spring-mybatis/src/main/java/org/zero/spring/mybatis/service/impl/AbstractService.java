package org.zero.spring.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.zero.spring.mybatis.config.BaseService;
import org.zero.spring.mybatis.config.MyMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2018/8/29
 * @description 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements BaseService<T> {

    @Autowired
    protected MyMapper<T> mapper;

    private Class<T> modelClass; // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T model) {
        mapper.insertSelective(model);
    }

    @Override
    public void save(List<T> models) {
        mapper.insertList(models);
    }

    @Override
    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    @Override
    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findBy(String fieldName, Object value) throws Exception {
        T model = modelClass.newInstance();
        Field field = modelClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(model, value);
        return mapper.selectOne(model);
    }

    @Override
    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

}

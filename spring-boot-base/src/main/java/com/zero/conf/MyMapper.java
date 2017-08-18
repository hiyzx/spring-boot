package com.zero.conf;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * 继承自己的MyMapper <br/>
 * FIXME 特别注意，该接口不能被扫描到，否则会出错
 *
 * @author yezhaoxing
 * @date : 2017/4/17
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, SelectByIdsMapper<T> {
}

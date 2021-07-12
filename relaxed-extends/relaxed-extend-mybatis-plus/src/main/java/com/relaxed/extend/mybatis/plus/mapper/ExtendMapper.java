package com.relaxed.extend.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

import static com.baomidou.mybatisplus.core.toolkit.Constants.COLLECTION;

/**
 * 所有的 Mapper接口 都需要继承当前接口 如果想自己定义其他的全局方法， 您的全局 BaseMapper 需要继承当前接口
 *
 * @author lingting 2020/5/27 11:39
 */
public interface ExtendMapper<T> extends BaseMapper<T> {

	/**
	 * 批量插入数据 实现类 {@link InsertBatchSomeColumn}
	 * @param list 数据列表
	 * @return int 改动行
	 * @author lingting 2020-08-26 22:11
	 */
	int insertBatchSomeColumn(@Param("collection") Collection<T> list);

	/**
	 * list insert
	 * @param list
	 * @return
	 */
	int insertBatch(@Param(COLLECTION) Collection<T> list);

}
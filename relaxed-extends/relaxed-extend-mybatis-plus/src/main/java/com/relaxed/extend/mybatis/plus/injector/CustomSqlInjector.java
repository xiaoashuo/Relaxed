package com.relaxed.extend.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Yakir
 * @Topic CustomSqlInjector
 * @Description
 * @date 2021/6/10 10:05
 * @Version 1.0
 */
@RequiredArgsConstructor
public class CustomSqlInjector extends DefaultSqlInjector {

	private final List<AbstractMethod> methods;

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		List<AbstractMethod> list = super.getMethodList(mapperClass);
		list.addAll(this.methods);
		return list;
	}

}

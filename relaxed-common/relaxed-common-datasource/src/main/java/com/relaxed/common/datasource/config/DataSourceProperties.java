/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.relaxed.common.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author lengleng
 * @date 2019-05-14
 * <p>
 */
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * jdbc url
	 */
	private String url;

	/**
	 * 驱动类型
	 */
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from gen_datasource_conf where del_flag = 0";

	/**
	 * 结果集属性列
	 */
	@NestedConfigurationProperty
	private ResultSetKey rsk = new ResultSetKey();

	/**
	 * 结果集属性 数据库对应列名称
	 */
	@Data
	class ResultSetKey {

		/**
		 * db name key
		 */
		private String name = "name";

		/**
		 * db url key
		 */
		private String url = "url";

		/**
		 * db username key
		 */
		private String username = "username";

		/**
		 * db password key
		 */
		private String password = "password";

	}

}

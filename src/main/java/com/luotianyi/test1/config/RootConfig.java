package com.luotianyi.test1.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.luotianyi.test1.config.RootConfig.WebPackage;

/**
 * @title: RootConfig.java
 * @package com.shuyuntu.report.config
 * @description: 扫描所有包但不包括 "com.shuyuntu.report.web" 以及 "WebConfig.java"
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月19日 下午2:45:18
 * @version 1.0
 */
@Configuration
@Import(value = HibernateConfig.class)
@ComponentScan(basePackages = { "com.luotianyi.test1" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = WebPackage.class), @Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class) })
public class RootConfig {

	// 扫描除了web以外所有的包
	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com.luotianyi.test1\\.web")); // 此处正则表达式 匹配包名// 【.代表匹配任意字符，所以最后一个.最好转义】
		}
	}
}

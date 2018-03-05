package com.luotianyi.test1.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.luotianyi.test1.mvc.AuthInterceptor;

/**
 * @title: WebConfig.java
 * @package com.shuyuntu.report.config
 * @description: TODO
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月19日 下午2:42:57
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.luotianyi.test1.web")
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 配置视图解析器【jsp,html...】
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/html/"); // 设置前缀
		resolver.setSuffix(".html"); // 设置后缀
		return resolver;
	}

	/**
	 * 配置静态资源的处理 使用servlet容器中默认的servlet 而不是使用DispatcherServlet
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 支持controller返回 String
	 * 
	 * @author 毕泗平
	 * @date 2017年3月24日 下午5:57:56
	 * @return
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		return new StringHttpMessageConverter();
	}

	/**
	 * 支持@RequestBody 返回json 数据
	 * 
	 * @author 毕泗平
	 * @date 2017年3月24日 下午5:59:59
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}

	/**
	 * 消息类型转换器配置
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	/**
	 * 拦截器设置（权限限制、路径拦截）
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns添加拦截路径 "/**" 全部拦截
		// excludePathPatterns 设置无需拦截的路径
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/login",
				"/assets/**", "/resources/**");
		super.addInterceptors(registry);
	}
}

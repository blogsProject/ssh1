package com.luotianyi.test1.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @title: WebInitializer.java
 * @package com.shuyuntu.report.config
 * @description: web初始化
 * @copyright: shuyuntu.com
 * @author 毕泗平
 * @date 2017年3月19日 下午2:38:29
 * @version 1.0
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebConfig.class }; // DispatcherServlet 配置
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // 都通过
	}

	/**
	 * 配置multipart解析器（使用Servlet3.0解析multipart请求）
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 1. 设置临时目录（文件系统中的一个绝对目录，上传文件将会临时写入该目录）
		// 2. 上传文件的最大容量（以字节为单位），默认没有限制
		// 3. 整个multipart请求的最大容量（以字节为单位），默认没有限制
		// 4.
		// 在上传的过程中，如果文件大小达到了一个指定最大容量（以字节为单位），将会写入到临时文件路径中，默认值为0，也就是所有上传的文件都会写入到磁盘上
		registration.setMultipartConfig(new MultipartConfigElement("/uploads"));
		registration.setInitParameter("dispatchOptionsRequest", "true");
	}

}

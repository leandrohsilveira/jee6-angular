package br.com.lhs.jee6angular.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.commons.io.IOUtils;

public class ResourceStreamUtil implements Serializable {

	private static final long serialVersionUID = -7749321811894842594L;

	private ResourceStreamUtil() {
	}

	public static InputStream getResource(Class<?> clazz, String path) {
		return clazz.getClassLoader().getResourceAsStream(path);
	}

	public static String getResourceAsString(Class<?> clazz, String path) throws IOException {
		return IOUtils.toString(ResourceStreamUtil.getResource(clazz, path));
	}

}

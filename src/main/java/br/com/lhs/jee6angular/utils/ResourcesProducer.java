package br.com.lhs.jee6angular.utils;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

@RequestScoped
public class ResourcesProducer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	public Logger produceJbossLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass());
	}

}
